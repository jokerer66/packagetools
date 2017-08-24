package helper.file;


import bean.AndroidLog;
import bean.IosLog;
import bean.Project;
import helper.date.MyTime;
import helper.log.MyLogTest;
import org.springframework.web.multipart.MultipartFile;
import process.pack.IosPack;
import service.DealAndroidLog;
import service.DealGlobalset;
import service.DealIosLog;
import service.DealProject;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MyFile {

	private static MyFile myfile;
	public static MyFile getInstance() {
		if (myfile == null) {
			synchronized (MyFile.class) {
				if (myfile == null) {
					myfile = new MyFile();
				}
			}
		}
		return myfile;
	}

	public MyFile() {
	}

	//新增文件
		public void addFile(String filePath){
			
			File file =new File(filePath);
			
			try {
				//判断文件是否存在，如果存在则询问是直接覆盖还是不操作
			if(file.exists()){
				int yn = JOptionPane.showConfirmDialog( null , "文件已存在是否覆盖？选择是覆盖，选择否返回。" , "文件选择", JOptionPane.YES_NO_OPTION ) ;
				if(yn == 0){
					file.delete();
					file.createNewFile();}
				
			}else{		
				//文件不存在则直接创建
					file.createNewFile();
			}
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showConfirmDialog(null, "文件创建失败","提醒", JOptionPane.CLOSED_OPTION);
				System.out.println("文件创建失败！");
			}
		}
		
		//创建文件夹
		public void addDir(String filePath){
			
			File file = new File(filePath);
			if(file.exists()&&file.isDirectory()){
				System.out.println("文件已存在，不需要创建");
				
			}else{
				file.mkdir();
			}
		}

		//创建多层文件夹
		public void addDirs(String filePath){
			
			File file = new File(filePath);
			
			if(file.exists()&&file.isDirectory()){
				System.out.println("文件已存在，不需要创建");
				
			}else{
				file.mkdirs();
			}
			
			
		}

		//批量创建文件夹
		public void addDirs(String lujing,String dirName,int num){
			
			String str = null;
			File file =null;
			if(!(num>0)){JOptionPane.showConfirmDialog(null, "批量增加文件时数量输入错误","提醒", JOptionPane.CLOSED_OPTION);}
			for(int i=0;i<=num;i++){
				str = lujing+i+dirName;
				file = new File(str);
				if(file.exists()&&file.isDirectory()){
					int yn = JOptionPane.showConfirmDialog(null, "批量增加文件夹时文件已存在,是否覆盖？","提醒", JOptionPane.YES_NO_OPTION);
					if(yn==0){
						file.delete();
						file.mkdir();
				}else{
				file.mkdir();}
				}}
		}

		//删除文件夹
		public void deleteDir(String filePath){
			
			File file = new File(filePath);
			if(file.exists()&&file.isDirectory()){
				file.delete();
				
			}else{
				JOptionPane.showConfirmDialog(null, "创建文件夹时，文件夹已存在","提醒", JOptionPane.CLOSED_OPTION);
			}
		}

		//删除文件夹下的所有文件
		 public static boolean delFolder(String folderPath) {
			 boolean flag = true;
			 boolean flag1 = true;
			 boolean flag2 = true;
		     try {

				 if(new File(folderPath).exists()) {
					 flag1 = delAllFile(folderPath); //删除完里面所有内容

					 String filePath = folderPath;

					 File myFilePath = new File(filePath);

					 myFilePath.delete(); //删除空文件夹
				 }else{
					 System.out.println("要删除的文件夹不存在");
				 }

		     } catch (Exception e) {

		       e.printStackTrace(); 
		       flag = false;
		     }
		     if(flag == true && flag1 == true){
		     flag2 = true;

			 }else{
		    	 flag2 = false;
		     }
		     return flag2;
		}

		//删除指定文件夹下所有文件
		//param path 文件夹完整绝对路径
		public static boolean delAllFile(String path) {
		       boolean flag = true;
		       File file = new File(path);
		       if (!file.exists()) {
		         return flag;
		       }
		       if (!file.isDirectory()) {
		         return flag;
		       }
		       String[] tempList = file.list();
		       File temp = null;
		       for (int i = 0; i < tempList.length; i++) {
		          if (path.endsWith(File.separator)) {
		             temp = new File(path + tempList[i]);
		          } else {
		              temp = new File(path + File.separator + tempList[i]);
		          }
		          if (temp.isFile()) {
		             temp.delete();
		          }
		          if (temp.isDirectory()) {
		             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
		             delFolder(path + "/" + tempList[i]);//再删除空文件夹
		             flag = true;
		          }
		       }
		       return flag;
		     }

		//复制文件
		public boolean copyFileChar(String oldFile,String newFile){
			
			boolean flag = true;
			File file = new File(oldFile);
			File file1 = new File(newFile);
			if(file.exists()){
			try {
				
				//使用缓冲区读取写入的方式来复制文件，效率高
				BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
				BufferedOutputStream bw = new BufferedOutputStream((new FileOutputStream(file1,false)));
				byte[] b=new byte[1024];
				int c=0;
				//行读取写入，行为空则停止读取写入
				while((c=br.read(b))!=-1){
					bw.write(b,0,c);
				}
				bw.flush();
				System.out.println(" "+oldFile+"...替换成功.");
				br.close();
				bw.close();
				
			}catch (Exception e){
				System.out.println("复制时文件读写异常");
				flag = false;
			}}else{
				System.out.println(oldFile+"文件不存在");
				flag = false;
			}
			return flag;
			
		}
		
		//删除文件
		public void deleteFile(String filePath){
			
			File file = new File(filePath);
			//存在则删除，不存在则提示
			if(file.exists()){
				file.delete();
			}else{
				System.out.println(filePath+"文件不存在不需要删除");
			}
		}
		
		//移动文件
		public void moveFile(String oldFile,String newFile){
			
			//首先复制文件，然后删除源文件
			copyFileChar(oldFile,newFile);
			deleteFile(oldFile);
		}

    	//放入文件路径，通过行数定位需要修改的位置，将该行数位置的字符串替换为第三个参数的内容字符串
		public boolean editFile(String filePath,String lineContent,String contents){

		String filePath_bak = filePath+".bak";
		
		boolean flag = true;
		File file = new File(filePath);
		File file1 = new File(filePath_bak);
		
		if(file.exists()){
		try {
			
			//使用缓冲区读取写入的方式来复制文件，效率高
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1,false),"UTF-8"));
			
			String str = null;
			int i=1;
			while((str=br.readLine())!=null){
	            
				if(str.contains(lineContent)){
					//由于myFile.editFile(svnInfo_android.getLocal_path()+"/app/build.gradle","debuggable false","debuggable true"); 导致需要对于文件中的jniDebuggable参数进行特殊处理
					if(str.contains("jniDebuggable false")){
						bw.write(str);
						bw.write("\r\n");
						i++;
					}else{
						bw.write(contents);
						bw.write("\r\n");
						i++;
					}
				}else{
					
				bw.write(str);
				bw.write("\r\n");
				i++;
				}
	
			}
			bw.flush();
			System.out.println(" "+filePath+"...替换成功.");
			br.close();
			bw.close();

		}catch (Exception e){
			System.out.println("复制时文件读写异常");

			flag = false;
		}}else{
			System.out.println(filePath+"文件不存在");

			flag = false;
		}
		
		//文件删除以及替换
		file.delete();
		file1.renameTo(file);
		
		
		return flag;
		
		
	}
	
		//传入文件路径读取文件内容，返回一个字符串
		public String readFile(String filePath){
		
		String result =null;
		try{
		File file = new File(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		
		String str = null;
		
		while((str=br.readLine())!=null){
			result += str;
		}
		
		br.close();
		}catch(IOException e){
			System.out.println("读取文件失败");
		}
		return result;
	}
	
		//传入文件路径以及文件内容，写入文件
		public boolean writerFile(String filePath,String context){
			boolean flag = true;
			File file = new File(filePath);
			String str = context;
			try{
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false)));
		
				if(str!=null){
					bw.write(str);
				}
			bw.flush();
			bw.close();
			}catch(IOException e){
			flag = false;
			System.out.println("写入文件失败");
		}
		
		return flag;
	}

		//传入文件路径以及文件内容，写入文件
		public boolean addcontextFile(String filePath,String context){
			boolean flag = true;
			File file = new File(filePath);
			String str = context;
			try{
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));

				if(str!=null){
					bw.write(str);
				}
				bw.flush();
				bw.close();
		}catch(IOException e){
			flag = false;
			System.out.println("写入文件失败");
		}

		return flag;
	}

		//调试使用的代码
		public boolean fileIn(String filePath){
		
		
			String filePath_bak = filePath+".bak";
		
			boolean flag = true;
			File file = new File(filePath);
			File file1 = new File(filePath_bak);
		
			if(file.exists()){
				try {
			
						//使用缓冲区读取写入的方式来复制文件，效率高
						BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
						BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1,false)));
			
						String str = null;
					int i=1;
					while((str=br.readLine())!=null){
	            
					String str1 = str.replace("\"", "\\\"");
					if(i==1){
						bw.write("\"");
						bw.write(str1);
						bw.write("\\r\\n\"");
						bw.write("\r\n");
					i++;
				}else{
					
				bw.write("+\"");
				bw.write(str1);
				bw.write("\\r\\n\"");
				bw.write("\r\n");
				i++;
				}
	
			}
			bw.flush();
			System.out.println(" "+filePath+"...替换成功.");
			br.close();
			bw.close();
			
		}catch (Exception e){
			System.out.println("复制时文件读写异常");
			flag = false;
		}}else{
			System.out.println(filePath+"文件不存在");
			flag = false;
		}
		
		return flag;
		
	}
	
	//通过输入的代码地址读取到AndroidManifest.xml文件的内容中的第5行，找到版本号，并返回
	public String versionRead(String filePath,int line){
		
		String filestr = filePath;
		File file = new File(filestr);
		
		String str = "";
		String str1 = "";
		String str2[] = null;
		int i = 1;
		
		try{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		while((str = br.readLine())!=null){
			
			System.out.println(str);
			if(i==line){
				
				str1 = str;
				System.out.println(str1);
				break;
			}
			i++;
		}
		
		str2 = str1.split("\"");
		str = str2[1];
		
		System.out.println(str2[1]);
		
		br.close();
		}catch(IOException e){
			
			System.out.println("读取AndroidManifest.xml文件出现异常："+e.getMessage());
		}
		
		return str;
	}


	/*** 读取修改时间的方法2*/
	public String getModifiedTime(String filePath){

		String back_str="";

		try {
			File f = new File(filePath);
			Calendar cal = Calendar.getInstance();
			long time = f.lastModified();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			cal.setTimeInMillis(time);
			//System.out.println("修改时间[2] " + formatter.format(cal.getTime()));

			back_str = formatter.format(cal.getTime());

		}catch(Exception e){
			e.printStackTrace();
		}

		return back_str;
		//输出：修改时间[2]    2009-08-17 10:32:38
	}

	//返回文件夹内的所有文件的文件名、文件最近一次修改时间
	public Map<String,String> back_filelist(String dir_path,String file_ext,int limit) {

			MyFile mf = new MyFile();

			//Hashtable<String,String> ht = new Hashtable<String, String>();
			Map<String,String> ht = new TreeMap<String,String>(new Comparator<String>(){
				public int compare(String obj1,String obj2){
					//降序排序
					return obj2.compareTo(obj1);
				}
			});
			int view_length = limit;

			File ff = new File(dir_path);
			File[] fl = ff.listFiles();
			StringBuffer sb = new StringBuffer();

			if(file_ext.equals("")){

				for (int i = 0; i < fl.length; i++) {
					//去掉隐藏文件 && fl[i].getName().endsWith(file_ext)
					if (!fl[i].getName().startsWith(".")) {
						sb.append(fl[i].getName()).append("#");
					}
				}
			}else {

				for (int i = 0; i < fl.length; i++) {
					//去掉隐藏文件 && fl[i].getName().endsWith(file_ext)
					if (!fl[i].getName().startsWith(".") && fl[i].getName().endsWith(file_ext)) {
						sb.append(fl[i].getName()).append("#");
					}
				}
			}
			String[] strs = sb.toString().split("#");
		//排序
			Arrays.sort(strs);


				if (strs.length < view_length) {
					view_length = strs.length;
				} else {
					view_length = limit;
				}


			for (int i = 0; i < view_length; i++) {

				//System.out.println(i+"="+strs[i]);
					ht.put(strs[i], mf.getModifiedTime(dir_path+"/"+strs[i]));

			}

		System.out.println(ht.values());

			return ht;
		}
	/**
	 * @Method: makeFileName
	 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	 * @param filename 文件的原始名称
	 * @return uuid+"_"+文件的原始名称
	 */
	private String makeFileName(String filename){ //2.jpg
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}
	/**
	 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	 * @param filename 文件名，要根据文件名生成存储目录
	 * @param savePath 文件存储路径
	 * @return 新的存储目录
	 */
	private String makePath(String filename,String savePath){
		//得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
		int hashcode = filename.hashCode();
		int dir1 = hashcode&0xf; //0--15
		int dir2 = (hashcode&0xf0)>>4; //0-15
		//构造新的保存目录
		String dir = savePath + File.separator + dir1 + File.separator + dir2; //upload\2\3 upload\3\5
		//File既可以代表文件也可以代表目录
		File file = new File(dir);
		//如果目录不存在
		if(!file.exists()){
			//创建目录
			file.mkdirs();
		}
		return dir;
	}

	public String zipFile(String file_path) {

		String result_zip = "0";

		File f = new File(file_path);
		try {
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] buf = new byte[1024];
			int len;
			FileOutputStream fos = new FileOutputStream(file_path + ".zip");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ZipOutputStream zos = new ZipOutputStream(bos);//压缩包
			ZipEntry ze = new ZipEntry(f.getName());//这是压缩包名里的文件名
			zos.putNextEntry(ze);//写入新的 ZIP 文件条目并将流定位到条目数据的开始处

			while ((len = bis.read(buf)) != -1) {
				zos.write(buf, 0, len);
				zos.flush();
			}
			bis.close();
			zos.close();
		}catch (IOException e){

			e.printStackTrace();
			result_zip = "1";
		}


		return result_zip;
	}

	//返回当前文件夹下最新的文件名称，参数:文件夹位置，后缀名，返回值:文件名
	public String output_new_file_name(String forder_name,String file_ext){

		String new_file_name = "";
		File file = new File(forder_name);
		File file1 = null;

		if(file.exists() && file.isDirectory() && file_ext.endsWith("mobileprovision")){

			File[] files = file.listFiles();

			file1 = files[0];

			for(int i=1;i<files.length;i++){

				if(file1.lastModified() < files[i].lastModified()){
					file1 = files[i];
				}

			}
			new_file_name = file1.getName().split("\\.")[0];
		}
		//当文件夹不存在或后缀不是mobileprovision则返回空
		return new_file_name;
	}

	/**
	 *
	 * @param dir_path 路径
	 * @param file_ext 0-endwith , 1-beginwith
	 * @param keyword 关键字
     * @return
     */
	public boolean hasfile(String dir_path,String file_ext,String keyword) {

		File ff = new File(dir_path);
		File[] fl = ff.listFiles();
		if(fl == null){
			return false;
		}
		StringBuffer sb = new StringBuffer();

		if(file_ext.contains("0")){

			for (int i = 0; i < fl.length; i++) {
				if (fl[i].getName().endsWith(keyword)) {
					return true;
				}
			}
		}else {

			for (int i = 0; i < fl.length; i++) {
				if (fl[i].getName().startsWith(keyword)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 *
	 * @param file
	 * @param output_file_path
	 * @param productname
	 * @param use_name_flag   0,普通文件上传,1,android上传,2,ios上传
	 *                  ios_filename        ios上传时所用的文件夹名字
     * @return
     */
	public Boolean uploadstandFile(MultipartFile file,String output_file_path,String productname,String use_name_flag,String ios_filename){
		Boolean flag_upload = false;
		String move_forder_to_downipa= null;
		String cp_to_tomcat = null;
		String pack_ios_excute_path = DealGlobalset.getInstance().getGlobalset().getTomcat_path() + "/webapps/downipa/sh_bash";
		String pack_ios_excute_path2 = DealGlobalset.getInstance().getGlobalset().getTomcat_path() + "/webapps/downipa/cp_to_tomcat";

		try {
			if(!new File(output_file_path).exists()){
				new File(output_file_path).mkdirs();
			}
			if(use_name_flag.equals("0")){
				File newFile = new File(output_file_path+"/"+file.getOriginalFilename());
				file.transferTo(newFile);
			}else if(use_name_flag.equals("1")){
				File newFile = new File(output_file_path+"/"+productname+".apk");
				file.transferTo(newFile);
			}else if(use_name_flag.equals("2")){
				Project project_ios = DealProject.getInstance().getProjectByProductnameLimited(productname,"ios");
				if(project_ios == null) return false;
				File newFile = new File(output_file_path+"/"+productname+".ipa");
				file.transferTo(newFile);
				cp_to_tomcat = IosPack.get_cp_to_tomcat_str("",ios_filename,DealGlobalset.getInstance().getGlobalset().getTomcat_path()+"/webapps/downipa/"+DealGlobalset.getInstance().getGlobalset().getDownipa_filename());
				MyScript.getInstance().create_exe_file(pack_ios_excute_path, "echo 11", "info");
				MyScript.getInstance().create_exe_file(pack_ios_excute_path2, "#!/bin/sh\n" + cp_to_tomcat, "info");
				move_forder_to_downipa = "sh "+pack_ios_excute_path2+" "+project_ios.getProductname()+" "+project_ios.getProductname()+" "+project_ios.getIosbundleid()+" "+
						output_file_path+"/"+productname+".ipa";

				MyLogTest.getInstance().level("info","step 4.2:move_forder_to_tomcat is : "+move_forder_to_downipa);
				MyScript.getInstance().run_command_file(move_forder_to_downipa, pack_ios_excute_path, "info");
			}
			flag_upload = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag_upload;
	}

	public Boolean uploadandroidFile(MultipartFile file,String output_file_path,String version,String productname){
		String filename = "z_"+productname+"_"+version+"_"+MyTime.getInstance().getDate_nopoint()+"_googleplay";
		String android_path = DealGlobalset.getInstance().getGlobalset().getAndroid_packpath();
		Boolean flag_upload = false;

		AndroidLog androidLog =new AndroidLog();
		if(uploadstandFile(file,output_file_path+"/"+filename+"/online",productname,"1","")){
			androidLog.setPackname(filename);
			androidLog.setMain_version(version);
			androidLog.setSvn_version("");
			androidLog.setFile_name(filename);
			androidLog.setStore_root_path(android_path.substring(0,android_path.lastIndexOf("/")));
			androidLog.setStore_path(android_path.substring(android_path.lastIndexOf("/")+1));
			androidLog.setSvn_url("");
			androidLog.setProduct_name(productname);
			androidLog.setEdition("");
			androidLog.setPlatform("android");
			androidLog.setMode("");
			androidLog.setOn_off("1");
			androidLog.setNums(1);
			androidLog.setIp("ip");
			androidLog.setPack_time(MyTime.getInstance().getTime_database());
			androidLog.setIsstore("0");
			androidLog.setViewflag("1");
			androidLog.setExone("");
			androidLog.setExtwo("");
			androidLog.setExthree("");
			DealAndroidLog.getInstance().addAndroidLog(androidLog);

			flag_upload =true;
		}else{
			flag_upload =false;
		}
		return flag_upload;
	}

	public Boolean uploadiosFile(MultipartFile file,String output_file_path,String version,String productname){
		String filename = "z_"+productname+"_"+version+"_"+MyTime.getInstance().getDate_nopoint()+"_appstore";
		String ios_path = DealGlobalset.getInstance().getGlobalset().getIos_packpath();
		Boolean flag_upload = false;

		IosLog iosLog = new IosLog();
		if(uploadstandFile(file,output_file_path+"/"+filename+"/online",productname,"2",filename)){
			iosLog.setPackname(filename);
			iosLog.setMain_version(version);
			iosLog.setSvn_version("");
			iosLog.setFile_name(filename);
			iosLog.setStore_root_path(ios_path.substring(0,ios_path.lastIndexOf("/")));
			iosLog.setStore_path(ios_path.substring(ios_path.lastIndexOf("/")+1));
			iosLog.setSvn_url("");
			iosLog.setProduct_name(productname);
			iosLog.setEdition("");
			iosLog.setPlatform("ios");
			iosLog.setMode("");
			iosLog.setOn_off("1");
			iosLog.setNums(1);
			iosLog.setIp("ip");
			iosLog.setPack_time(MyTime.getInstance().getTime_database());
			iosLog.setIsstore("0");
			iosLog.setViewflag("1");
			iosLog.setExone("");
			iosLog.setExtwo("");
			iosLog.setExthree("");
			DealIosLog.getInstance().addIosLog(iosLog);

			flag_upload =true;
		}else{
			flag_upload =false;
		}
		return flag_upload;
	}
}
