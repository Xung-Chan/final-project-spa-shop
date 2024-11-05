document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault(); // Ngăn chặn form gửi đi ngay lập tức

    // Hiển thị hộp thoại xác nhận
    const confirmation = confirm("Bạn có xác nhận muốn thanh toán không?");

    if (confirmation) {
        // Nếu người dùng xác nhận, hiển thị thông báo thanh toán thành công
        alert("Thanh toán thành công!");

        // Chuyển hướng về trang chủ (thay đổi 'index.html' thành trang chủ của bạn)
        window.location.href = 'index.html';
    }
});
