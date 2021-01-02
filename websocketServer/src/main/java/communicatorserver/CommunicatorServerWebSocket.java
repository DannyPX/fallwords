package communicatorserver;

import Commands.*;
import GameHub.GameHub;
import Responses.*;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import communicatorshared.WebSocketMessage;
import communicatorshared.WebSocketMessageType;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import Responses.*;

@ServerEndpoint(value = "/communicator/")
public class CommunicatorServerWebSocket {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static final List<Session> sessions = new ArrayList<>();
    public static final Map<String, List<Session>> games = new HashMap<>();
    public final Gson gson;
    public final GameHub gameHub;

    public CommunicatorServerWebSocket() {
        this.gson = new Gson();
        this.gameHub = new GameHub();
    }

    @OnOpen
    public void onConnect(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onText(String message, Session session) {
        handleMessage(message, session);
    }

    @OnClose
    public final void onClose(CloseReason reason, Session session) {
        sessions.remove(session);
    }

    @OnError
    public final void onError(Throwable cause, Session session) {
        cause.printStackTrace();
    }

    private void handleMessage(String json, Session session) {
        WebSocketMessage message;

        if (json.isEmpty()) {
            return;
        }

        try {
            message = gson.fromJson(json, WebSocketMessage.class);

            if (message == null) {
                return;
            }
        }
        catch (JsonSyntaxException jsonSyntaxException) {
            jsonSyntaxException.printStackTrace();
            return;
        }

        switch (message.getType()) {
            case Subscribe:
                SubscribeCommand subscribeCommand = gson.fromJson(message.getContent(), SubscribeCommand.class);
                handleSubscribe(subscribeCommand, session);
                break;
            case Unsubscribe:
                UnSubscribeCommand unSubscribeCommand = gson.fromJson(message.getContent(), UnSubscribeCommand.class);
                handleUnSubscribe(unSubscribeCommand, session);
                break;
            case StartGame:
                StartGameCommand startGameCommand = gson.fromJson(message.getContent(), StartGameCommand.class);
                handleStartGame(startGameCommand);
            case CreateGame:
                CreateGameCommand createGameCommand = gson.fromJson(message.getContent(), CreateGameCommand.class);
                handleCreateGame(createGameCommand, session);
            case EnterWord:
                EnterWordCommand enterWordCommand = gson.fromJson(message.getContent(), EnterWordCommand.class);
                handleEnterWord(enterWordCommand, session);
        }
    }

    private void handleSubscribe(SubscribeCommand command, Session session) {
        if (!isValidAuthenticationKey(command.getAuthenticationKey()) || !hasPermission(command.getAuthenticationKey(), command.getGameCode())) {
            return;
        }

        String gameCode = command.getGameCode();
        try {
            gameHub.getGames().stream().filter(o -> o.getGameCode().equals(gameCode)).findFirst().get().addPlayer(command.getUser());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SubscribeResponse response = new SubscribeResponse(command);
        String json;
        try {
            WebSocketMessage message = new WebSocketMessage(WebSocketMessageType.Subscribe, gson.toJson(response));
            json = gson.toJson(message);

        }
        catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            return;
        }

        createIfNotExist(command.getGameCode());
        games.get(command.getGameCode()).add(session);
        sendToOthers(command.getGameCode(), json, session);
    }

    private void handleUnSubscribe(UnSubscribeCommand command, Session session) {
        if (!boardExists(command.getGameCode())) {
            return;
        }

        String gameCode = command.getGameCode();
        try {
            gameHub.getGames().stream()
                    .filter(o -> o.getGameCode().equals(gameCode))
                    .findFirst().get().removePlayer(command.getUser());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        UnSubscribeResponse response = new UnSubscribeResponse(command);
        String json;
        try {
            WebSocketMessage message = new WebSocketMessage(WebSocketMessageType.Subscribe, gson.toJson(response));
            json = gson.toJson(message);

        }
        catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            return;
        }

        games.get(command.getGameCode()).remove(session);
        sendToOthers(command.getGameCode(), json, session);
    }

    private void handleStartGame(StartGameCommand command) {
        if (!boardExists(command.getGameCode())) {
            return;
        }

        StartGameResponse response;
        String gameCode = command.getGameCode();
        try {
            gameHub.getGames().stream()
                    .filter(o -> o.getGameCode().equals(gameCode))
                    .findFirst().get().startGame();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        response = new StartGameResponse(gameCode);
        String json;
        try {
            WebSocketMessage message = new WebSocketMessage(WebSocketMessageType.StartGame, gson.toJson(response));
            json = gson.toJson(message);
        }
        catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            return;
        }

        for (Session session : games.get(gameCode)) {
            session.getAsyncRemote().sendText(json);
        }
    }

    private void handleCreateGame(CreateGameCommand command, Session session) {
        CreateGameResponse response;
        String gameCode = null;
        try {
            gameCode = gameHub.createGame();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        } finally {
            response = new CreateGameResponse(gameCode);
        }

        String json;
        try {
            WebSocketMessage message = new WebSocketMessage(WebSocketMessageType.CreateGame, gson.toJson(response));
            json = gson.toJson(message);
        }
        catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            return;
        }
        session.getAsyncRemote().sendText(json);
    }

    private void handleEnterWord(EnterWordCommand command, Session session) {
        EnterWordOwnResponse responseOwn;
        EnterWordResponse response;
        String newWord = null;
        String gameCode = command.getGameCode();


        try {
            newWord = gameHub.getGames().stream()
                    .filter(o -> o.getGameCode().equals(gameCode))
                    .findFirst().get().enterWord(command.getPlayer(), command.getWord());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        finally {
            responseOwn = new EnterWordOwnResponse(gameCode, newWord, newWord != null);
        }

        String jsonOwn;
        try {
            WebSocketMessage messageOwn = new WebSocketMessage(WebSocketMessageType.CreateGame, gson.toJson(responseOwn));
            jsonOwn = gson.toJson(messageOwn);
        }
        catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            return;
        }

        session.getAsyncRemote().sendText(jsonOwn);
        if(newWord != null) {
            response = new EnterWordResponse(gameCode, newWord);
            String json;
            try {
                WebSocketMessage message = new WebSocketMessage(WebSocketMessageType.CreateGame, gson.toJson(response));
                json = gson.toJson(message);
            }
            catch (JsonSyntaxException exception) {
                exception.printStackTrace();
                return;
            }
            sendToOthers(command.getGameCode(), json, session);
        }
    }

    private boolean isValidAuthenticationKey(String key) {
        return true;
    }

    private boolean hasPermission(String authenticationKey, String gameCode) {
        return true;
    }

    private void createIfNotExist(String gameCode) {
        if (!games.containsKey(gameCode)) {
            games.put(gameCode, new ArrayList<>());
        }
    }

    private boolean boardExists(String id) {
        return games.containsKey(id);
    }

    private void sendToOthers(String gameCode, String json, Session session) {
        List<Session> others = games.get(gameCode).stream().filter(item -> !(item.getId().equals(session.getId()))).collect(Collectors.toList());

        for (Session tempSession : others) {
            tempSession.getAsyncRemote().sendText(json);
        }
    }
}