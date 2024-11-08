const renderFeedback = function (feedbacks) {
    container = $("#feedbackContainer");
    html = '';
    feedbacks.forEach((feedback) => {
        let stars = '';
        for (let i = 0; i < feedback.rate; i++) {
            stars += `<i class="fas fa-star text-primary"></i>`;
        }
        for (let i = feedback.rate; i < 5; i++) {
            stars += `<i class="fas fa-star"></i>`;
        }
        html += `<div class="testimonial-item rounded p-4">
            <div class="row">
                <div class="col-4">
                    <div class="d-flex flex-column mx-auto">
                        <div class="rounded-circle mb-4" style="border: dashed; border-color: var(--bs-white);">
                            <img src="/customer/img/${feedback.imagePath}" class="img-fluid rounded-circle" alt="">
                        </div>
                        <div class="text-center">
                            <h4 class="mb-2 text-primary">${feedback.name}</h4>
                            <p class="m-0 text-white">Khách hàng</p>
                        </div>
                    </div>
                </div>
                <div class="col-8">
                    <div class="position-absolute" style="top: 20px; right: 25px;">
                        <i class="fa fa-quote-right fa-2x text-secondary"></i>
                    </div>
                    <div class="testimonial-content">
                        <div class="d-flex mb-4">`+ stars +
            `</div>
                        <p class="fs-5 mb-0 text-white">${feedback.description}
                        </p>
                    </div>
                </div>
            </div>
        </div>
        `;
    });
    container.html(html);
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 2000,
        center: false,
        dots: true,
        loop: true,
        margin: 25,
        nav: true,
        navText: [
            '<i class="bi bi-arrow-left"></i>',
            '<i class="bi bi-arrow-right"></i>'
        ],
        responsiveClass: true,
        responsive: {
            0: {
                items: 1
            },
            576: {
                items: 1
            },
            768: {
                items: 1
            },
            992: {
                items: 2
            },
            1200: {
                items: 2
            }
        }
    });
}
