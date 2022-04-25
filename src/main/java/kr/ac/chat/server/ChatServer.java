package kr.ac.chat.server;

import java.util.Vector;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatServer extends TextWebSocketHandler {
	private Vector<WebSocketSession> list;
	
	public ChatServer() {
		super();
		
		if(list == null)
			list = new Vector<>();
	}
	
//	연결이 시작된 후 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
//		접속한 사용자의 session을 저장
		list.add(session);
//		사용자의 ip를 출력
		System.out.println("연결 : " + session.getRemoteAddress());
	}
//	텍스트 메시지만 처리
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		String user = (String) session.getAttributes().get("user");
		
//		메시지 내용 출력
		System.out.println(String.format("메시지 : %s, %s", message.getPayload(), user));
//		다른 사용자에게 메시지를 보내줌
		for(WebSocketSession client : list) {
			client.sendMessage(new TextMessage(message.getPayload()));
		}
	}
//	연결이 끊긴 이후 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
//		나간 사용자 삭제
		list.remove(session);
//		사용자 정보 출력
		System.out.println("종료 : " + session.getRemoteAddress());
	}
	
}
