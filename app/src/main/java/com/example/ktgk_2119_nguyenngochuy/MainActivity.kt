package com.example.ktgk_2119_nguyenngochuy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2119_nguyenngochuy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding_119: ActivityMainBinding

    // Các biến lưu thông tin khoản chi tự đặt có hậu tố _119
    private var tenKhoanChi_119: String = ""
    private var soTien_119: Double = 0.0
    private var ghiChu_119: String = ""
    private var daThanhToan_119: Boolean = false
    private var coKhoanChiHopLe_119: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_119 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_119.root)

        // Thiết lập Toolbar tiêu đề theo yêu cầu
        setSupportActionBar(binding_119.toolbar119)
        supportActionBar?.title = "Nguyễn Ngọc Huy - 2415053122119 - Quản lý chi tiêu cá nhân"

        // Xử lý sự kiện nút Thêm khoản chi
        binding_119.btnThemKhoanChi119.setOnClickListener {
            themKhoanChi_119()
        }

        // Xử lý sự kiện nút Xem chi tiết khoản chi
        binding_119.btnXemChiTiet119.setOnClickListener {
            xemChiTietKhoanChi_119()
        }
    }

    // Hàm xử lý thêm khoản chi tự đặt có hậu tố _119
    private fun themKhoanChi_119() {
        val tenNhap_119 = binding_119.edtTenKhoanChi119.text.toString().trim()
        val soTienChuoi_119 = binding_119.edtSoTien119.text.toString().trim()
        val ghiChuNhap_119 = binding_119.edtGhiChu119.text.toString().trim()

        // 1. Kiểm tra tên khoản chi
        if (tenNhap_119.isEmpty()) {
            Toast.makeText(this, "Tên khoản chi không được để trống!", Toast.LENGTH_SHORT).show()
            binding_119.edtTenKhoanChi119.requestFocus()
            return
        }

        // 2. Kiểm tra số tiền
        if (soTienChuoi_119.isEmpty()) {
            Toast.makeText(this, "Số tiền không được để trống!", Toast.LENGTH_SHORT).show()
            binding_119.edtSoTien119.requestFocus()
            return
        }

        val soTienChuyenDoi_119 = soTienChuoi_119.toDoubleOrNull()
        if (soTienChuyenDoi_119 == null || !laSoTienHopLe_119(soTienChuyenDoi_119)) {
            Toast.makeText(this, "Số tiền phải là số hợp lệ và lớn hơn 0!", Toast.LENGTH_SHORT).show()
            binding_119.edtSoTien119.requestFocus()
            return
        }

        // Lưu thông tin khoản chi hợp lệ
        tenKhoanChi_119 = tenNhap_119
        soTien_119 = soTienChuyenDoi_119
        ghiChu_119 = ghiChuNhap_119
        daThanhToan_119 = false
        coKhoanChiHopLe_119 = true

        val trangThaiChuoi_119 = if (daThanhToan_119) "Đã thanh toán" else "Chưa thanh toán"
        binding_119.tvTrangThaiKhoanChi119.text =
            "Tên: $tenKhoanChi_119\nSố tiền: ${String.format("%,.0f", soTien_119)} VNĐ\nTrạng thái: $trangThaiChuoi_119"

        Toast.makeText(this, "Thêm khoản chi thành công!", Toast.LENGTH_SHORT).show()
    }

    // Hàm kiểm tra số tiền hợp lệ tự đặt có hậu tố _119
    private fun laSoTienHopLe_119(soTien_119: Double): Boolean {
        return soTien_119 > 0
    }

    // Hàm xem chi tiết khoản chi tự đặt có hậu tố _119
    private fun xemChiTietKhoanChi_119() {
        if (!coKhoanChiHopLe_119) {
            Toast.makeText(this, "Vui lòng thêm khoản chi hợp lệ trước khi xem chi tiết!", Toast.LENGTH_SHORT).show()
            return
        }
        // Tạm thời hiển thị Toast thông báo khi chưa chuyển sang ChiTietChiTieuActivity
        Toast.makeText(this, "Mở chi tiết khoản chi: $tenKhoanChi_119", Toast.LENGTH_SHORT).show()
    }
}