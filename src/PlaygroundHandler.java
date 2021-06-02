import java.util.Vector;

public class PlaygroundHandler
{
    Vector<Playground> playgrounds = new Vector<Playground>();
    public boolean addPlayground(Playground playground)
    {
        readPlaygroundsFile();
        if (playgrounds.add(playground))
        {
            writePlaygroundsFile();
            return true;
        }
        else
            return false;
    }
    //                  /!\ /!\   not implemented yet   /!\
    //                  /!\ /!\   not implemented yet   /!\
    public void writePlaygroundsFile()  //update the playgrounds database
    {

    }
    public void readPlaygroundsFile()   //update the vector from the database
    {

    }
}
