package com.lxit.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lxit.dao.MediaDao;
import com.lxit.dao.impl.DaoFactory;
import com.lxit.entity.Media;

@WebServlet("/mediaService")
public class MediaService extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 PrintWriter out = response.getWriter();
		 MediaDao mediaDao = DaoFactory.getMediaDao();
		 String message = "";
		 String uri = request.getRequestURI();
		 String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		 if ("/uploadFile".equals(path)){
			    //�ṩ����ʱ��һЩȱʡ����
	            DiskFileItemFactory factory = new DiskFileItemFactory();
	            
	            //����һ��������,����InputStream,�ý������Ὣ�����Ľ����װ��һ��FileItem����ļ���
	            //һ��FileItem�����Ӧһ������
	            ServletFileUpload sfu = new ServletFileUpload(factory);
	        
	            try {
	                Media media = new Media();
	                List<FileItem> items = sfu.parseRequest(request);
	                boolean flag = false;    //ת��ɹ����ı��
	                for(int i=0; i<items.size(); i++){
	                    FileItem item = items.get(i);
	                    //Ҫ�������ϴ��ļ�������ͨ�ı���
	                    if(item.isFormField()){//isFormField()Ϊtrue,��ʾ�ⲻ���ļ��ϴ�����
	                        //��ͨ����
	                        String paramName = item.getFieldName();
	                        /*
	                            String paramValue = item.getString();
	                            System.out.println("��������Ϊ:" + paramName + ", ��Ӧ�Ĳ���ֵΪ: " + paramValue);
	                        */
	                        if(paramName.equals("title")){
	                            media.setTitle(new String(item.getString().getBytes("ISO8859-1"),"UTF-8"));
	                        }
	                        if(paramName.equals("descript")){
	                            media.setDescript(new String(item.getString().getBytes("ISO8859-1"),"UTF-8"));
	                        }
	                        
	                    }else{
	                        //�ϴ��ļ�
	                        //System.out.println("�ϴ��ļ�" + item.getName());
	                        ServletContext sctx = this.getServletContext();
	                        //��ñ����ļ���·��
	                        String basePath = sctx.getRealPath("videos");
	                        //����ļ���
	                        String fileUrl= item.getName();
	                        //��ĳЩ����ϵͳ��,item.getName()�����᷵���ļ�����������,������·��
	                        String fileType = fileUrl.substring(fileUrl.lastIndexOf(".")); //��ȡ�ļ���ʽ
	                        //�Զ��巽ʽ�����ļ���
	                        String serialName = String.valueOf(System.currentTimeMillis());
	                        //��ת����ļ�
	                        File uploadFile = new File(basePath+"/temp/"+serialName + fileType);
	                        item.write(uploadFile);
	                        
	                        if(item.getSize()>500*1024*1024){
	                            message = "<li>�ϴ�ʧ�ܣ����ϴ����ļ�̫��,ϵͳ��������ļ�500M</li>";
	                        }
	                        String codcFilePath = basePath + "/" + serialName + ".flv";                //����ת��Ϊflv��ʽ���ļ��ı���·��
	                        String mediaPicPath = basePath + "/images" +File.separator+ serialName + ".jpg";    //�����ϴ���Ƶ��ͼ�ı���·��
	                        
	                        // ��ȡ���õ�ת�����ߣ�ffmpeg.exe���Ĵ��·��
	                        String ffmpegPath = getServletContext().getRealPath("/tools/ffmpeg.exe");
	                        
	                        media.setSrc("videos/" + serialName + ".flv");
	                        media.setPicture("videos/images/" +serialName + ".jpg");
	                       /* media.setUptime(DateTimeUtil.getYMDHMSFormat());*/
	                        
	                        //ת��
	                        
	                        flag = mediaDao.executeCodecs(ffmpegPath, uploadFile.getAbsolutePath(), codcFilePath, mediaPicPath);
	                    }
	                }
	                if(flag){
	                    //ת��ɹ�,�����ݱ�����Ӹ���Ƶ��Ϣ
	                    mediaDao.saveMedia(media);
	                    message = "<li>�ϴ��ɹ�!</li>";
	                }
	                
	                request.setAttribute("message", message);
	                request.getRequestDispatcher("media_upload.jsp").forward(request,response);
	            
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	                throw new ServletException(e);
	            }
		 }
		 if("/queryAll".equals(path)){
	            List<Media> mediaList;
	            try {
	                mediaList = mediaDao.queryALlMedia(0,5);
	                request.setAttribute("mediaList", mediaList);
	                request.getRequestDispatcher("media_list.jsp").forward(request, response);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		  if("/play".equals(path)){
	            String idstr = request.getParameter("id");
	            int mediaId = -1;
	            Media media = null;
	            if(null!=idstr){
	                mediaId = Integer.parseInt(idstr);
	            }
	            try {
	                media = mediaDao.queryMediaById(mediaId);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            request.setAttribute("media", media);
	            request.getRequestDispatcher("media_player.jsp").forward(request, response);
	        }
	}
}	
