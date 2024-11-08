(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner(0);


    // Initiate the wowjs
    new WOW().init();


    // Fixed Navbar
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.sticky-top').addClass('shadow-sm').css('top', '0px');
        } else {
            $('.sticky-top').removeClass('shadow-sm').css('top', '-200px');
        }
    });


    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({ scrollTop: 0 }, 1500, 'easeInOutExpo');
        return false;
    });


    // Pricing-carousel
    // $(".pricing-carousel").owlCarousel({
    //     autoplay: true,
    //     smartSpeed: 2000,
    //     center: false,
    //     dots: true,
    //     loop: true,
    //     margin: 25,
    //     nav: true,
    //     navText: [
    //         '<i class="bi bi-arrow-left"></i>',
    //         '<i class="bi bi-arrow-right"></i>'
    //     ],
    //     responsiveClass: true,
    //     responsive: {
    //         0: {
    //             items: 1
    //         },
    //         576: {
    //             items: 2
    //         },
    //         768: {
    //             items: 2
    //         },
    //         992: {
    //             items: 3
    //         },
    //         1200: {
    //             items: 4
    //         }
    //     }
    // });



    // Modal Video
    // $(document).ready(function () {
    //     var $videoSrc;
    //     $('.btn-play').click(function () {
    //         $videoSrc = $(this).data("src");
    //     });
    //     console.log($videoSrc);

    //     $('#videoModal').on('shown.bs.modal', function (e) {
    //         $("#video").attr('src', $videoSrc + "?autoplay=1&amp;modestbranding=1&amp;showinfo=0");
    //     })

    //     $('#videoModal').on('hide.bs.modal', function (e) {
    //         $("#video").attr('src', $videoSrc);
    //     })
    // });


    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 5,
        time: 2000
    });


})(jQuery);

ApiEndpoints = {
    allServices: "/service/services",
    servicesLimit: function (n) {
        return `/service/limit?limit=${n}`;
    },
    allFeedbacks: "/feedback/feedbacks",
    allEmployees: "/employee/employees",
    employeesLimit: function (n) {
        return `/employee/limit?limit=${n}`;
    },
    allPosts: "/post/posts",
    myInformation: "/customer/myInformation",
}
postEndpoints = {
    appointment: "/appointment",
    customer: "/customer"
}
async function fetchData(endpoint, callback, requestOption = {
    method: "GET"
}) {
    try {
        const response = await fetch(endpoint, requestOption);
        // if (!response.ok) {
        //     throw new Error(`Respone status:${response.status}`);
        // }
        const apiResponse = await response.json();
        callback(apiResponse);
    } catch (error) {
        console.error('Failed to fetch data:', error);
    }
}
