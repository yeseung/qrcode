import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {
        
        @GetMapping("")
        public ResponseEntity<byte[]> create(@RequestParam String url,
                               @RequestParam(value = "w", required = false, defaultValue = "200") int width,
                               @RequestParam(value = "h", required = false, defaultValue = "200") int height) throws WriterException, IOException {
            
            BitMatrix matrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height);
            
            try (ByteArrayOutputStream out = new ByteArrayOutputStream();) {
                MatrixToImageWriter.writeToStream(matrix, "PNG", out);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body(out.toByteArray());
            }
        }


    @PostMapping("/scan")
    public String scanQRCode(@RequestParam("file") MultipartFile file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                return "Invalid image file.";
            }
            
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);
            
            return result.getText();
        } catch (NotFoundException e) {
            return "QR code not found in the image.";
        } catch (IOException e) {
            return "Error processing the file.";
        }
    }

}
