// Hàm khôi phục bài đăng và bình luận từ localStorage
function loadPosts() {
    const posts = JSON.parse(localStorage.getItem("posts")) || [];
    posts.forEach(post => {
        displayPost(post);
    });
}

// Hàm hiển thị bài đăng
function displayPost(post) {
    const postsContainer = document.getElementById("postsContainer");

    // Tạo bài đăng mới
    const newPost = document.createElement("div");
    newPost.className = "post bg-white rounded p-4 shadow-sm mb-4";
    newPost.id = post.id;

    newPost.innerHTML = `
        <div class="d-flex align-items-center mb-3">
            <img src="/customer/img/user1.jpg" class="rounded-circle me-3" style="width: 50px; height: 50px;" alt="User">
            <div>
                <h6 class="mb-0">Tên Người Dùng</h6>
                <small class="text-muted">Ngày đăng: ${post.date} | Chủ đề: Làm đẹp</small>
            </div>
        </div>
        <p>${post.content}</p>
        <div class="d-flex justify-content-around mt-3">
            <button class="btn btn-light like-btn" onclick="toggleLike('${post.id}')">
                <i class="fa fa-thumbs-up"></i> Thích <span class="like-count">(0)</span>
            </button>
            <button class="btn btn-light" onclick="toggleCommentSection('${post.id}')">
                <i class="fa fa-comment"></i> Bình luận <span class="comment-count">(${post.comments.length})</span>
            </button>
            <button class="btn btn-light"><i class="fa fa-share"></i> Chia sẻ</button>
        </div>
        <!-- Comments Section -->
        <div class="comment-section bg-light rounded mt-3 p-3" id="commentSection${post.id}">
            <h6 class="text-secondary">Bình luận</h6>
            <div id="commentList${post.id}" class="comment-list"></div>
            <textarea id="commentInput${post.id}" class="form-control mb-2" rows="2" placeholder="Viết bình luận..." 
                      onkeypress="addCommentOnEnter(event, 'commentList${post.id}', 'commentInput${post.id}', '${post.id}')"></textarea>
            <button class="btn btn-primary btn-sm" onclick="addComment('commentList${post.id}', 'commentInput${post.id}', '${post.id}')">Bình luận</button>
        </div>
    `;

    // Thêm bình luận đã lưu vào bài đăng
    post.comments.forEach(comment => {
        addCommentToDOM(`commentList${post.id}`, comment);
    });

    // Thêm bài đăng mới vào danh sách
    postsContainer.prepend(newPost);
}

// Hàm để tạo bài đăng mới
function createPost() {
    var postContent = document.getElementById("newPostContent").value.trim();

    if (postContent !== "") {
        var postId = `post${Date.now()}`;
        var date = new Date().toLocaleString();

        // Lưu bài đăng vào localStorage
        const posts = JSON.parse(localStorage.getItem("posts")) || [];
        posts.push({ id: postId, content: postContent, date: date, comments: [] });
        localStorage.setItem("posts", JSON.stringify(posts));

        // Hiển thị bài đăng
        displayPost({ id: postId, content: postContent, date: date, comments: [] });
        document.getElementById("newPostContent").value = ""; // Xóa nội dung textarea
    }
}

// Hàm để thêm bình luận
function addComment(commentListId, commentInputId, postId) {
    var commentInput = document.getElementById(commentInputId);
    var commentText = commentInput.value.trim();

    if (commentText !== "") {
        addCommentToDOM(commentListId, commentText);
        commentInput.value = ""; // Xóa nội dung textarea

        // Cập nhật số lượng bình luận
        var commentCountElement = document.querySelector(`#${postId} .comment-count`);
        var currentCount = parseInt(commentCountElement.textContent.match(/\d+/)[0]);
        commentCountElement.textContent = `(${currentCount + 1})`;

        // Lưu bình luận vào localStorage
        const posts = JSON.parse(localStorage.getItem("posts")) || [];
        const postIndex = posts.findIndex(post => post.id === postId);
        if (postIndex > -1) {
            posts[postIndex].comments.push(commentText);
            localStorage.setItem("posts", JSON.stringify(posts));
        }
    }
}

// Hàm để thêm bình luận vào DOM
function addCommentToDOM(commentListId, commentText) {
    var commentList = document.getElementById(commentListId);
    var newComment = document.createElement("div");
    newComment.className = "d-flex align-items-start mb-3";
    newComment.innerHTML = `
        <img src="/customer/img/user2.jpg" class="rounded-circle me-2" style="width: 40px; height: 40px;" alt="User">
        <div>
            <small class="text-muted">Tên Người Dùng</small>
            <p>${commentText}</p>
        </div>
    `;
    commentList.prepend(newComment); // Thêm bình luận mới ở đầu danh sách
}

// Hàm để xử lý sự kiện thích
function toggleLike(postId) {
    var likeCountElement = document.querySelector(`#${postId} .like-count`);
    var currentCount = parseInt(likeCountElement.textContent.match(/\d+/)[0]);
    likeCountElement.textContent = `(${currentCount + 1})`;
}

// Hàm để hiển thị hoặc ẩn phần bình luận
function toggleCommentSection(postId) {
    var commentSection = document.getElementById(`commentSection${postId}`);
    commentSection.classList.toggle('d-none'); // Chuyển trạng thái hiển thị
}

// Hàm để xử lý nhấn Enter để thêm bình luận
function addCommentOnEnter(event, commentListId, commentInputId, postId) {
    if (event.key === 'Enter') {
        event.preventDefault(); // Ngăn chặn hành động mặc định
        addComment(commentListId, commentInputId, postId);
    }
}

// Tải bài đăng từ localStorage khi trang được tải
window.onload = loadPosts;
