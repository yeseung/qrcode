# qrcode


	implementation group: 'com.google.zxing', name: 'javase', version: '3.5.3'
	implementation group: 'com.google.zxing', name: 'core', version: '3.5.3'

---

	String url = "/qrcode?url=" + URLEncoder.encode("https://www.google.com/search?newwindow=1&sca_esv=73cfaf4f66504ccb&sxsrf=ADLYWIKy24iJti2jQt3oMU5YwfH1zL-NrA:1732089047902&q=java+qr%EC%BD%94%EB%93%9C+%EC%8A%A4%EC%BA%94&sa=X&ved=2ahUKEwjXrK3DtuqJAxW73TQHHUj7C_wQ1QJ6BAg3EAE&biw=2441&bih=1215&dpr=2", "UTF-8") + "&w=400&h=400";
	model.addAttribute("url", url);

 	String newQRCode = QRCodeGenerator.generateQRCodeBase64("https://github.com/yeseung", 100, 100);
	model.addAttribute("newQRCode", newQRCode);
      
---

 	<img src="/qrcode?url=https://github.com/yeseung"><br>
	<img th:src="${url}"><br>
	<img th:src="'data:image/png;base64,' + ${newQRCode}">
 

---

<img src="https://raw.githubusercontent.com/yeseung/qrcode/refs/heads/master/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA%202024-11-21%20%E1%84%8B%E1%85%A9%E1%84%8C%E1%85%A5%E1%86%AB%2011.09.58.jpg" width="450px"></img>
