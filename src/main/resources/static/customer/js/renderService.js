const renderService = function (services) {
    container = $("#serviceContainer");
    html = '';
    services.forEach((service) => {
        html += ` <div class="col-lg-6">
                <div class="services-item bg-light border-4 border-end border-primary rounded p-4">
                    <div class="row align-items-center">
                        <div class="col-8">
                            <div class="services-content text-end">
                                <h3>${service.name}</h3>
                                <p>${service.description}</p>
                                <a href="/customer/login"
                                    class="btn btn-primary btn-primary-outline-0 rounded-pill py-2 px-4">Đặt
                                    Ngay</a>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="services-img d-flex align-items-center justify-content-center rounded">
                                <img src="/customer/img/${service.imagePath}" class="img-fluid rounded" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        `;
    });
    container.html(html);
}

