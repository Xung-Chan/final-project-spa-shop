const renderEmployee = function (employees) {
    container = $("#employeeContainer");
    html = '';
    employees.forEach((employee) => {
        html += ` <div class="col-md-6 col-lg-6 col-xl-3">
                <div class="team-item">
                    <div class="team-img rounded-top">
                        <img src="/customer/img/${employee.image}" class="img-fluid w-100 rounded-top bg-light" alt="">
                    </div>
                    <div class="team-text rounded-bottom text-center p-4">
                        <h3 class="text-white">${employee.fullName}</h3>
                        <p class="mb-0 text-white">Chuyên gia Spa &amp; Beauty</p>
                    </div>
                    <div class="team-social">
                        <a class="btn btn-light btn-light-outline-0 btn-square rounded-circle mb-2" href="#"><i class="fab fa-twitter"></i></a>
                        <a class="btn btn-light btn-light-outline-0 btn-square rounded-circle mb-2" href="#"><i class="fab fa-facebook-f"></i></a>
                        <a class="btn btn-light btn-light-outline-0 btn-square rounded-circle mb-2" href="#"><i class="fab fa-linkedin-in"></i></a>
                        <a class="btn btn-light btn-light-outline-0 btn-square rounded-circle" href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
        `;
    });
    container.html(html);
}

