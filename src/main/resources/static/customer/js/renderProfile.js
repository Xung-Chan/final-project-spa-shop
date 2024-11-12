const renderProfile = function (profile) {
    container = $("#profileContainer");
    html = ` <div class="services-img d-flex align-items-center justify-content-center rounded">
                <img src="/customer/img/${profile.image}" class="img-fluid rounded" alt="">
            </div>
            <div>ID người dùng</div>
            <div class="col-xl-12">
            <input type="text" id="userID" value="${profile.id}" class="form-control bg-white border-0 py-3 px-4"
            placeholder="Mã người dùng" >
            </div>
            <div>username</div>
            <div class="col-xl-12">
            <input type="text" name="username" value="${profile.username}" class="form-control bg-white border-0 py-3 px-4"
            placeholder="Tên đăng nhập" >
            </div>
            <div>fullname</div>
            <div class="col-xl-12">
            <input type="text" name="fullName" value="${profile.fullName}" class="form-control bg-white border-0 py-3 px-4"
            placeholder="Họ tên" >
            </div>
            <div>email</div>
            <div class="col-xl-12">
            <input type="email" name="email" value="${profile.email}" class="form-control bg-white border-0 py-3 px-4"
            placeholder="Email" >
            </div>
            <div>Số điện thoại</div>
            <div class="col-xl-12">
            <input type="text" name="phoneNumber" value="${profile.phoneNumber}" class="form-control bg-white border-0 py-3 px-4"
            placeholder="Số điện thoại" >
            </div>
            <div>Ngày sinh</div>
            <div class="col-xl-12">
            <input type="date" name="birth" value="${profile.birth}" class="form-control bg-white border-0 py-3 px-4"
            placeholder="Ngày sinh" >
            </div>
        `;
    container.html(html);
}

