const renderAppointment = function (appointments) {
    container = $("#appointmentContainer");
    let html = ""
    appointments.forEach((appointment, index) => {
        statusAppointment = "<td>Chưa thanh toán</td>"
        operate = `<button href="/customer/cancelappointment" class="btn btn-danger btn-sm" onclick="cancelAppointment(${appointment.id})">Hủy</button>
                    <a href="/customer/payment?id=${appointment.id}" class="btn btn-success btn-sm">Thanh toán</a>`
        services = ""
        appointment.services.forEach((x) => {
            services += `<div>${x}</div>`
        })
        if (appointment.status) {
            statusAppointment = "<td>Đã thanh toán</td>";
            operate = ""
        }
        html += `   <tr>
                        <td>${index + 1}</td>
                        <td>${appointment.date}</td>
                        <td>${services}</td>
                        ${statusAppointment}
                        <td>
                            ${operate}
                        </td>
                    </tr>
        `;
    });
    container.html(html);
}
const cancelAppointment = async function (appointmentID) {
    userConfirm = confirm("Bạn có chắc muốn hủy lịch hẹn ?");
    if (userConfirm) {
        let response = await fetch(`/appointment?id=${appointmentID}`, {
            method: "DELETE",
        })
        if (response.ok) {
            alert("Huủy lịch hẹn thành công")
            await renderData()
        }
        else
            alert("Thao tác thất bại")
    }
}
async function renderData() {
    await fetchData("/appointment/myAppointment", (apiResponse) => {
        if (!apiResponse.success) {
            throw new Error(apiResponse.message)
        }
        renderAppointment(apiResponse.result);
    })

}
const renderListServices = function (services) {
    container = $("#listService");
    let html = ""
    services.forEach((service) => {
        html += ` <div class="form-check">
                    <input type="checkbox" id="service-${service.id}" class="form-check-input"  name="serviceIDs" value="${service.id}">
                    <label class="form-check-label" for="service-${service.id}">${service.name}</label>
                </div>
        `;
    });
    container.html(html);

}
