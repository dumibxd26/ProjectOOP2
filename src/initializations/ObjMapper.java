package initializations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjMapper {

    private ObjectMapper mapper = new ObjectMapper();
    private ArrayNode output;

    private static
    ObjMapper instance = null;

    /**
     * Private constructor for singleton
     * @return
     */
    public static ObjMapper getInstance() {
        if (instance == null) {
            instance = new ObjMapper();
        }
        return instance;
    }

    /**
     * Method to creade a new output array node for a specific test
     */
    public void createNewOutput() {
       output = createArrayNode();
    }

    /**
     * Method to create and return a node
     * @return
     */
    public ObjectNode createNode() {
        return mapper.createObjectNode();
    }

    /**
     * Method to create and return an array node
     * @return
     */
    public ArrayNode createArrayNode() {
        return mapper.createArrayNode();
    }

    /**
     * Getter for the mapper
     * @return
     */
    public ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * Getter for the output
     * @return
     */
    public ArrayNode getOutput() {
        return output;
    }
}
