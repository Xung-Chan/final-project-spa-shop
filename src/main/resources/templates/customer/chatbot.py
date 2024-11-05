from flask import Flask, request, jsonify
from flask_cors import CORS

app = Flask(__name__)
CORS(app)

def get_response(user_input):
    responses = {
        "chào": "Xin chào! Chào mừng bạn đến với Sephire Beauty & Spa. Tôi có thể giúp gì cho bạn?",
        "hi": "Xin chào! Chào mừng bạn đến với Sephire Beauty & Spa. Tôi có thể giúp gì cho bạn?",
        "xin chào": "Chào bạn! Sephire Beauty & Spa có thể hỗ trợ gì cho bạn hôm nay?",
        "hello": "Chào bạn! Sephire Beauty & Spa có thể hỗ trợ gì cho bạn hôm nay?",
        "tạm biệt": "Tạm biệt! Hy vọng sớm gặp lại bạn tại Sephire Beauty & Spa!",
        "bye": "Tạm biệt! Hy vọng sớm gặp lại bạn tại Sephire Beauty & Spa!",
        "bạn khỏe không?": "Tôi ở đây để giúp bạn với mọi thắc mắc về Sephire Beauty & Spa!",

        # Thông tin chung
        "các dịch vụ ở đây là gì?": "Chúng tôi cung cấp các dịch vụ như chăm sóc da, massage thư giãn, tẩy lông và nhiều dịch vụ khác.",
        "dịch vụ làm đẹp nào có ở đây?": "Tại Sephire, chúng tôi có dịch vụ chăm sóc da, điều trị cơ thể và massage thư giãn.",
        "có chương trình khuyến mãi nào không?": "Dạ có! Chúng tôi có các chương trình khuyến mãi theo mùa. Vui lòng kiểm tra với chúng tôi hoặc ghé thăm website để biết thêm chi tiết!",

        # Dịch vụ cụ thể
        "dịch vụ chăm sóc da mặt bao gồm những gì?": "Dịch vụ chăm sóc da mặt của chúng tôi bao gồm làm sạch, tẩy da chết và dưỡng ẩm, phù hợp với loại da của bạn.",
        "ở đây có các liệu pháp thư giãn không?": "Dạ có! Chúng tôi cung cấp các gói massage toàn thân và liệu pháp thư giãn chuyên biệt.",
        "tẩy lông có an toàn tại sephire beauty & spa không?": "Dạ an toàn! Dịch vụ tẩy lông của chúng tôi sử dụng phương pháp an toàn và hiệu quả với đội ngũ chuyên nghiệp.",
        "có những loại massage nào?": "Chúng tôi cung cấp nhiều loại massage như massage Thụy Điển, massage mô sâu và massage đá nóng.",

        # Giá cả và gói dịch vụ
        "giá dịch vụ của bạn là bao nhiêu?": "Giá cả khác nhau tùy theo dịch vụ. Bạn có thể nói rõ hơn hoặc truy cập website của chúng tôi để xem chi tiết.",
        "có gói dịch vụ nào cho da nhạy cảm không?": "Dạ có! Chúng tôi có các gói dịch vụ đặc biệt cho da nhạy cảm.",
        "có gói liệu trình dài hạn không?": "Dạ có, chúng tôi cung cấp các gói liệu trình, bao gồm cả các gói chăm sóc dài hạn để đạt hiệu quả tốt nhất.",

        # Đặt lịch và sắp xếp
        "làm thế nào để đặt lịch hẹn?": "Bạn có thể đặt lịch hẹn qua số điện thoại hoặc trên website của chúng tôi.",
        "tôi có cần đặt lịch trước không?": "Khuyến khích đặt lịch trước để đảm bảo thời gian phù hợp cho bạn.",
        "tôi có thể hủy hoặc đổi lịch hẹn không?": "Dạ có, bạn có thể hủy hoặc đổi lịch hẹn nếu thông báo cho chúng tôi trước 24 giờ.",

        # Thông tin sản phẩm và liệu pháp
        "có sử dụng sản phẩm thiên nhiên không?": "Dạ có, chúng tôi sử dụng nhiều loại sản phẩm thiên nhiên và hữu cơ.",
        "sản phẩm nào dùng cho chăm sóc da mặt?": "Chúng tôi sử dụng các sản phẩm chất lượng cao, được bác sĩ da liễu khuyên dùng và phù hợp với nhiều loại da.",
        "liệu pháp nào tốt cho da dầu?": "Chúng tôi khuyên bạn sử dụng liệu pháp chăm sóc da mặt đặc biệt cho da dầu, giúp kiểm soát dầu và giảm bóng nhờn.",

        # Trải nghiệm khách hàng
        "nhân viên ở đây có kinh nghiệm không?": "Nhân viên của chúng tôi được đào tạo bài bản và có chứng chỉ cho mọi dịch vụ mà chúng tôi cung cấp.",
        "một buổi liệu trình kéo dài bao lâu?": "Thời gian tùy thuộc vào loại liệu trình, thường từ 30 đến 90 phút.",
        "sephire có khu vực thư giãn không?": "Dạ có, chúng tôi có khu vực nghỉ ngơi thoải mái để bạn thư giãn trước và sau liệu trình.",
        
        # Phản hồi mặc định khi không nhận diện được câu hỏi
        "default": "Tôi ở đây để giúp bạn! Bạn có thể hỏi lại về các dịch vụ của Sephire Beauty & Spa không?",
    }
    
    # Trả về câu trả lời dựa trên câu hỏi của người dùng hoặc phản hồi mặc định nếu không tìm thấy
    return responses.get(user_input.lower(), responses["default"])

@app.route('/chat', methods=['POST'])
def chat():
    user_input = request.json.get('message')
    response = get_response(user_input)
    return jsonify({'response': response})

if __name__ == '__main__':
    app.run(debug=True)