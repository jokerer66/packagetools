package helper.svn;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.*;

import java.io.File;

public class MySVN {

	private static SVNClientManager ourClientManager;

	private static MySVN mysvn;

	public static MySVN getInstance() {
		if (mysvn == null) {
			synchronized (MySVN.class) {
				if (mysvn == null) {
					mysvn = new MySVN();
				}
			}
		}
		return mysvn;
	}

	public MySVN() {
	}

	//update
	public String doUpdate(String url,String uname,String upwd,String localFile){

		long versionNum = 0L;
		String result = "null";
		SVNRepositoryFactoryImpl.setup();

		SVNURL repositoryURL = null;

		try{

			repositoryURL = SVNURL.parseURIDecoded(url);

			ISVNOptions options = SVNWCUtil.createDefaultOptions(true);

			ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options, uname, upwd);

			File updateFile = new File(localFile);

			//递归清理工作副本，删除未完成的工作副本锁定，并恢复未完成的操作
			//SVNWCClient wcClient = ourClientManager.getWCClient();
			//wcClient.doCleanup(updateFile);

			SVNUpdateClient updateClient = ourClientManager.getUpdateClient();
			updateClient.setIgnoreExternals(false);

			//**********对svn doupdate下来的代码冲突进行处理，直接让svn服务器覆盖本地代码*********
			DefaultSVNOptions svnOptions = (DefaultSVNOptions) updateClient.getOptions();
			//配置一个 ConflictResolverHandler
			svnOptions.setConflictHandler(new ConflictResolverHandler());
			//************************************************************

			//进行更新代码操作
			versionNum = updateClient.doUpdate(updateFile, SVNRevision.HEAD, SVNDepth.INFINITY,false,false);

			result = versionNum+"";
		}catch(SVNException e){

			result = "error";
		}

		return result;
	}

	//check out
	public String doCheckOut(String svnurl,String uname,String upwd,String localfile){

		String result = "null";
		long workingVersion = 0L;
		SVNRepositoryFactoryImpl.setup();

		SVNURL repositoryURL = null;

		try{

			repositoryURL = SVNURL.parseURIDecoded(svnurl);

		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);

		ourClientManager = SVNClientManager.newInstance((DefaultSVNOptions) options,uname,upwd);

		File wcDir = new File(localfile);

		SVNUpdateClient updateClient = ourClientManager.getUpdateClient();

		updateClient.setIgnoreExternals(false);

		//********************此种方法获取的版本号是console中得reversion版本号，非正常的提交修订版本号***********
			/*SVNStatusClient svnStatusClient = ourClientManager.getStatusClient();
			long hi = svnStatusClient.doStatus(wcDir, false).getRevision().getNumber();

			System.out.println("hi2="+hi);*/

		//************************
		workingVersion = updateClient.doCheckout(repositoryURL, wcDir, SVNRevision.HEAD, SVNRevision.HEAD, SVNDepth.INFINITY,false);

			result = workingVersion+"";

		}catch(SVNException e){
			result = "error";
		}
		return result;
	}

	//获取修订版本号的方法
	public String getversion(String url,String name,String pwd){

	String svnversion = "null";
		try {

			//DAVRepositoryFactory.setup();
			SVNRepositoryFactoryImpl.setup();

			SVNRepository repository = null;
			repository = SVNRepositoryFactory.create(SVNURL.parseURIDecoded(url));
			ISVNAuthenticationManager authManager =
					SVNWCUtil.createDefaultAuthenticationManager(name, pwd);
			repository.setAuthenticationManager(authManager);
			SVNDirEntry entry = repository.info(".", -1);

			svnversion = entry.getRevision()+"";


		}catch(SVNException e){

			e.printStackTrace();
		svnversion="error";
		}
	return svnversion;
	}

}
