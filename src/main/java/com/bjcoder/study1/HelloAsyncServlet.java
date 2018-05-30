package com.bjcoder.study1;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/async",asyncSupported=true)
public class HelloAsyncServlet extends HttpServlet {
	
	@Override
	protected void doGet(final HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1��֧���첽����asyncSupported=true
		//2�������첽ģʽ
		System.out.println("���̳߳ص��߳�xx��ʼ������"+Thread.currentThread()+"==>"+System.currentTimeMillis());
		final AsyncContext startAsync = req.startAsync();
		
		//3��ҵ���߼������첽����;��ʼ�첽����
		startAsync.start(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("���̳߳��߳�xx��ʼ������"+Thread.currentThread()+"==>"+System.currentTimeMillis());
					sayHello();
					startAsync.complete();
					//��ȡ���첽������
					AsyncContext asyncContext = req.getAsyncContext();
					//4����ȡ��Ӧ
					ServletResponse response = asyncContext.getResponse();
					response.getWriter().write("hello async...");
					System.out.println("���̳߳ص��߳�xx����������"+Thread.currentThread()+"==>"+System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		});		
		System.out.println("���̳߳ص��߳�xx����������"+Thread.currentThread()+"==>"+System.currentTimeMillis());
	}

	public void sayHello() throws Exception{
		System.out.println(Thread.currentThread()+" processing...");
		Thread.sleep(3000);
	}
}