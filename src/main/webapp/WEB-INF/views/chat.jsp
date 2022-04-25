<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script>
	const url = "ws://" + window.location.hostname + ":" + window.location.port + "/chat/chatserver";
	const socket = new WebSocket(url);
	
	let connect = false;
	
	socket.onopen = () => {
		connect = true;
		alert('서버에 연결 되었습니다.');
	};
	socket.onclose = () => {
		connect = false;
		alert('서버 연결이 종료 되었습니다.')
	};
	socket.onmessage = (msg) => {
		const chat = document.querySelector('#chat');
		console.dir(msg);
		const li = document.createElement('li');
		li.innerText = msg.data;
		chat.appendChild(li);
		console.log(msg.data);
	};
	
	function send(){
		if(connect){
			const nickname = document.querySelector('#nickname');
			const msg = document.querySelector('#msg');
			
			socket.send(nickname.value + ': ' + msg.value);
			
			msg.value = '';
		}
	}
</script>
</head>
<body>
	<div>
		<div>
			<ul id="chat">
				
			</ul>
		</div>
		<hr />
		<div>
			<label for="nickname">발신자:</label>
			<input type="text" id="nickname" value="익명"/>
		</div>
		<div>
			<label for="msg">메세지:</label>
			<input type="text" id="msg" />
		</div>
		<div>
			<button onclick="send()">보내기</button>
		</div>
	</div>
</body>
</html>