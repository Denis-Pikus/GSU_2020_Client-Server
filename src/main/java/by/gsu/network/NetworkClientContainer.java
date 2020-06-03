package by.gsu.network;

import by.gsu.utils.Container;

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;

public class NetworkClientContainer<T extends Serializable> implements Container<T> {

    private final Socket socket;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public NetworkClientContainer(String host, int port) throws IOException {
        socket = new Socket(host, port);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public int size() {
        Request request = new Request(Type.SIZE, null);
        try {
            oos.writeObject(request);
            oos.flush();
            Response response = (Response) ois.readObject();
            return (int) response.getPayload();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void add(T element) {
        Request request = new Request(Type.ADD, element);
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(int index, T element) {
        Request request = new Request(Type.SET, new UpdatePayload(index, element));
        try{
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int index) {
        Request request = new Request(Type.DELETE, index);
        try{
            oos.writeObject(request);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<T> getAll() {
        Request request = new Request(Type.GET, null);
        try {
            oos.writeObject(request);
            oos.flush();
            Response response = (Response) ois.readObject();
            return (Collection<T>) response.getPayload();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
