# Ứng dụng To-Do List (BT10_Kotlin)

Đây là một ứng dụng To-Do List đơn giản được phát triển trên nền tảng Android bằng ngôn ngữ Kotlin. Ứng dụng cho phép người dùng quản lý các công việc hàng ngày của mình một cách hiệu quả.

## Tính năng

*   **Đăng ký tài khoản:** Người dùng mới có thể tạo tài khoản để sử dụng ứng dụng.
*   **Đăng nhập:** Người dùng đã có tài khoản có thể đăng nhập để truy cập vào danh sách công việc của mình.
*   **Quản lý công việc:**
    *   **Thêm:** Thêm công việc mới vào danh sách.
    *   **Xem:** Hiển thị danh sách các công việc hiện có.
    *   **Sửa:** Chỉnh sửa nội dung của một công việc đã có.
    *   **Xóa:** Xóa một công việc ra khỏi danh sách.
*   **Lưu trữ cục bộ:** Toàn bộ dữ liệu về tài khoản và công việc được lưu trữ an toàn trên thiết bị bằng SQLite.

## Công nghệ sử dụng

*   **Ngôn ngữ:** Kotlin
*   **Kiến trúc:** Giao diện người dùng được xây dựng bằng XML Layouts.
*   **Cơ sở dữ liệu:** SQLite để lưu trữ dữ liệu cục bộ.

## Cấu trúc dự án

*   `LoginActivity.kt`: Xử lý logic cho màn hình đăng nhập.
*   `RegisterActivity.kt`: Xử lý logic cho màn hình đăng ký.
*   `MainActivity.kt`: Màn hình chính, hiển thị danh sách công việc và xử lý các thao tác thêm, sửa, xóa.
*   `DBHelper.kt`: Lớp quản lý cơ sở dữ liệu SQLite, bao gồm việc tạo bảng và thực hiện các truy vấn CRUD (Create, Read, Update, Delete).
*   `res/layout/`: Chứa các tệp tin XML định nghĩa giao diện người dùng cho các màn hình (Activity).

## Hướng dẫn cài đặt và chạy ứng dụng

1.  Sao chép (clone) dự án này về máy của bạn.
2.  Mở dự án bằng Android Studio.
3.  Đợi cho Gradle đồng bộ hóa (sync) tất cả các thư viện cần thiết.
4.  Chạy ứng dụng trên máy ảo (emulator) hoặc thiết bị Android thật.

