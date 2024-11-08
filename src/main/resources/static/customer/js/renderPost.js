const renderPost = function (posts) {
    container = $("#postContainer");
    html = '';
    posts.forEach((post) => {
        html += ` <div class="post bg-white rounded p-4 shadow-sm mb-4" id="post1">
                    <div class="d-flex align-items-center mb-3">
                        <img src="/customer/img/${post.imagePath}" class="rounded-circle me-3" style="width: 50px; height: 50px;" alt="User">
                        <div>
                            <h6 class="mb-0">${post.customerName}</h6>
                            <small class="text-muted">Ngày đăng: ${post.createdAt} | Chủ đề: Làm đẹp</small>
                        </div>
                    </div>
                    <p>${post.content}</p>
                    
                    <div class="d-flex justify-content-around mt-3">
                        <button class="btn btn-light like-btn" onclick="toggleLike('post1')">
                            <i class="fa fa-thumbs-up"></i> Thích <span class="like-count">(${post.likeNumber})</span>
                        </button>
                    </div>
                
                </div>
        `;
    });
    container.html(html);
}

