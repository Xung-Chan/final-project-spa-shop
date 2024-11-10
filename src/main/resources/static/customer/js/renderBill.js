const renderAppointment = function (appointment) {
    const container = $("#appointmentContainer");
    const html = `
        <div class="appointment">
            <h3>Hóa đơn #${appointment.id}</h3>
            <p><strong>Tên khách hàng:</strong> ${appointment.customerName}</p>
            <p><strong>Mã khách hàng:</strong> ${appointment.customerID}</p>
            <p><strong>Ngày:</strong> ${appointment.date}</p>
            <p><strong>Dịch vụ:</strong> ${Array.from(appointment.services).join(", ")}</p>
            <p><strong>Giảm giá:</strong> -${appointment.discount}%</p>
            <p><strong>Thành tiền:</strong> ${appointment.cost * 1000}VND</p>
        </div>
    `;
    container.html(html);
};

const renderData = async function (id) {
    await fetchData("/appointment/" + id, (apiResponse) => {
        if (!apiResponse.success) {
            throw new Error(apiResponse.message)
        }
        renderAppointment(apiResponse.result);
    })
}

