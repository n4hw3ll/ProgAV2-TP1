package Intermedio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class Servicio {

    private final List<Nodo> queue = new ArrayList<>();

    public Nodo addNode(Nodo nodo) {
        nodo.setMessageId(this.getNextMessageId());
        this.queue.add(nodo);
        return nodo;
    }

    public List<Nodo> getQueue() {
        return this.queue;
    }

    public Nodo removeNode() {
        return this.queue.remove(0);
    }

    public int getNextMessageId() {
        if (this.queue.isEmpty()) {
            return 1;
        }
        Nodo lastNode = this.queue.get(this.queue.size() - 1);
        return lastNode.getMessageId() + 1;
    }
}