import com.sun.istack.internal.Nullable;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionListener;
import java.io.File;


public class FileOpener {
    @Nullable
    public static void ShowFileDialog(IFileSelectedEventHandler eventHandler, FileFilter filter){
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(filter);
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        ActionListener onSelect=e -> {
            File f=fc.getSelectedFile();
            eventHandler.onSelect(f);
        };
        fc.addActionListener(onSelect);
        fc.showOpenDialog(null);
    }
    interface IFileSelectedEventHandler {
        void onSelect(File f);

    }
}
