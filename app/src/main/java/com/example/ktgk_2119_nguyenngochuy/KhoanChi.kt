package com.example.ktgk_2119_nguyenngochuy

import java.io.Serializable
import java.util.Locale

class KhoanChi(
    var maKhoanChi_119: String = "",
    var tenKhoanChi_119: String = "",
    var soTien_119: Double = 0.0,
    var ghiChu_119: String = "",
    var daThanhToan_119: Boolean = false
) : Serializable {

    // Alias getters/setters để đảm bảo tương thích tên đề bài nếu cần
    val maKhoanChi: String get() = maKhoanChi_119
    val tenKhoanChi: String get() = tenKhoanChi_119
    val soTien: Double get() = soTien_119
    val ghiChu: String get() = ghiChu_119
    val daThanhToan: Boolean get() = daThanhToan_119

    // Hàm trả về chuỗi mô tả thông tin khoản chi tự đặt có hậu tố _119
    fun layThongTin_119(): String {
        val trangThai_119 = if (daThanhToan_119) "Đã thanh toán" else "Chưa thanh toán"
        val soTienDinhDang_119 = String.format(Locale.getDefault(), "%,.0f VNĐ", soTien_119)
        return "Mã: $maKhoanChi_119 | Tên: $tenKhoanChi_119 | Số tiền: $soTienDinhDang_119 | Ghi chú: $ghiChu_119 | Trạng thái: $trangThai_119"
    }

    // Hàm kiểm tra số tiền hợp lệ (> 0) tự đặt có hậu tố _119
    fun laSoTienHopLe_119(): Boolean {
        return soTien_119 > 0
    }
}