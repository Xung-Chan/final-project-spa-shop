const renderEditProfile = function (profile) {
    container = $("#profileContainer");
    html = `<input type="hidden" id="userID" value="${profile.id}" class="form-control bg-white border-0 py-3 px-4"
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
            <div>Avatar</div>
            <div class="col-xl-12">
                <input type="file" name="image" class="form-control bg-white border-0 py-3 px-4"
                placeholder="Avatar" >
            </div>
            
        `;
    container.html(html);
}

