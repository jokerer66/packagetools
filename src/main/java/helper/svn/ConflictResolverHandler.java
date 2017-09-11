package helper.svn;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.wc.*;
import helper.log.MyLogTest;
public class ConflictResolverHandler implements ISVNConflictHandler{

    MyLogTest logTest = new MyLogTest();

    @Override
    public SVNConflictResult handleConflict(SVNConflictDescription conflictDescription) throws SVNException {


        SVNConflictReason reason = conflictDescription.getConflictReason();
        SVNMergeFileSet mergeFiles = conflictDescription.getMergeFiles();

        //System.out.println("Conflict discovered in:" + mergeFiles.getWCFile());
        logTest.level("debug","Conflict discovered in:" + mergeFiles.getWCFile());

        //发生文件冲突时，使用svn update --accept theirs-full方式处理，使用svn服务器上的文件覆盖本地
        SVNConflictChoice choice = SVNConflictChoice.THEIRS_FULL;

       /* Scanner reader = new Scanner(System.in);
        if (reader.hasNextLine()) {
            String sVNConflictChoice = reader.nextLine();
            if (sVNConflictChoice.equalsIgnoreCase("mf")) {
                choice = SVNConflictChoice.MINE_FULL;
            } else if (sVNConflictChoice.equalsIgnoreCase("tf")) {
                choice = SVNConflictChoice.THEIRS_FULL;
            }
        }*/

        return new SVNConflictResult(choice, mergeFiles.getResultFile());

    }
}
