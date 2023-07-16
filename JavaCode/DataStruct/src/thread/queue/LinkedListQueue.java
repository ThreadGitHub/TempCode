package thread.queue;

/**
 * 链表实现队列
 */
public class LinkedListQueue<T> {
    /**
     * 头结点
     */
    private Node<T> headNode;

    /**
     * 尾节点
     */
    private Node<T> nextNode;

    /**
     * 队列长度
     */
    private int size;

    /**
     * 节点对象
     * @param <T>
     */
    class Node<T> {
        /**
         * 存数据
         */
        private T data;

        /**
         * 下一个节点
         */
        private Node<T> nextNode;

        public Node(T data) {
            this.data = data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getNextNode() {
            return nextNode;
        }
    }

    /**
     * 尾插
     */
    public void insert(T value) {
        //准备Node对象
        Node node = new Node(value);

        /**
         * 第一次插入
         */
        if (headNode == null && nextNode == null) {
            headNode = node;
            nextNode = node;
        } else {
            // 尾插
            nextNode.setNextNode(node);
            nextNode = node;
        }

        // 计算数量
        size++;
    }

    /**
     * 出队
     */
    public void remove() {
        Node<T> nextNode = this.headNode.nextNode;
        this.headNode = nextNode;
    }

    /**
     * 获取数量
     * @return
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        String result = "LinkedListQueue[";
        Node node = null;
        boolean flag = false;
        for (node = headNode; node != null; node = node.nextNode) {
            if (flag) {
                result += ",";
            }
            flag = true;
            result += node.data;
        }
        return result + "]";
    }

    public static void main(String[] args) {
        LinkedListQueue<String> linkedListQueue = new LinkedListQueue();
        linkedListQueue.insert("A");
        linkedListQueue.insert("B");
        linkedListQueue.insert("C");
        System.out.println(linkedListQueue);

        linkedListQueue.remove();
        System.out.println(linkedListQueue);
    }
}