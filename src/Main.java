import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import initializations.ObjMapper;
import readinput.Input;
import java.io.File;
import java.io.IOException;

public final class Main {

    private Main() { }

    /**
     *  Main function for reading input and writing output
     * @param args
     * @throws IOException
     */

    public static void main(final String[] args) throws IOException {

//        if (args[0].compareTo("/Users/dumib/Dev/ProjectOOP2/checker/resources/in/basic_10.json") == 0) {

            ObjectMapper mapper = new ObjectMapper();

            ObjMapper objMapper = ObjMapper.getInstance();
            Input inputData = mapper.readValue(new File(args[0]), Input.class);

            objMapper.createNewOutput();
            Run run = new Run();
            run.run(inputData);

            ObjectWriter objectWriter = objMapper.getMapper().writerWithDefaultPrettyPrinter();
            objectWriter.writeValue(new File(args[1]), objMapper.getOutput());
//        }
    }
}
