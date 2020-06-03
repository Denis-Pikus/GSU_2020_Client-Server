package by.gsu.network;

import by.gsu.model.GroceryStore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerContainer {

    private static ExecutorService executorService;
    private static CopyOnWriteArrayList<GroceryStore<?>> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws IOException {
        executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server started");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("new connection opened");
            process(socket);
        }
    }

    private static void process(Socket socket) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                    System.out.println("ready to communicate");

                    while (true) {
                        communicate(ois, oos);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void communicate(ObjectInputStream ois, ObjectOutputStream oos) throws Exception {

        Request request = (Request) ois.readObject();
        System.out.println(request);

        switch (request.getType()) {
            case ADD: {
                GroceryStore<?> element = (GroceryStore<?>) request.getPayload();
                list.add(element);
                break;
            }
            case GET: {
                Response response = new Response(list);
                for (GroceryStore<?> groceryStore : list) {
                    Thread.sleep(500);
                    System.out.println(groceryStore);
                }
                oos.writeObject(response);
                oos.flush();
                break;
            }
            case SET:{
                UpdatePayload updatePayload = (UpdatePayload) request.getPayload();
                list.set(updatePayload.getIndex(), (GroceryStore<?>) updatePayload.getElement());
                break;
            }
            case DELETE:{
                int index = (Integer) request.getPayload();
                list.remove(index);
                break;
            }
            case SIZE:{
                Response response = new Response(list.size());
                oos.writeObject(response);
                oos.flush();
                break;
            }
        }
    }
}
