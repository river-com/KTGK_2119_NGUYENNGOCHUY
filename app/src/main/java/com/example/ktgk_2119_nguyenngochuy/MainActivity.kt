package com.example.ktgk_2119_nguyenngochuy

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2119_nguyenngochuy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding_119: ActivityMainBinding

    // Sử dụng đối tượng KhoanChi làm mô hình dữ liệu chính
    private var khoanChiHienTai_119: KhoanChi? = null

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
        if (soTienChuyenDoi_119 == null) {
            Toast.makeText(this, "Số tiền phải là định dạng số hợp lệ!", Toast.LENGTH_SHORT).show()
            binding_119.edtSoTien119.requestFocus()
            return
        }

        // Khởi tạo đối tượng KhoanChi
        val maRandom_119 = "KC119_" + (System.currentTimeMillis() % 10000).toString()
        val khoanChiMoi_119 = KhoanChi(
            maKhoanChi_119 = maRandom_119,
            tenKhoanChi_119 = tenNhap_119,
            soTien_119 = soTienChuyenDoi_119,
            ghiChu_119 = ghiChuNhap_119,
            daThanhToan_119 = false
        )

        // Kiểm tra số tiền hợp lệ bằng hàm của lớp KhoanChi
        if (!khoanChiMoi_119.laSoTienHopLe_119()) {
            Toast.makeText(this, "Số tiền phải lớn hơn 0!", Toast.LENGTH_SHORT).show()
            binding_119.edtSoTien119.requestFocus()
            return
        }

        khoanChiHienTai_119 = khoanChiMoi_119

        binding_119.tvTrangThaiKhoanChi119.text = khoanChiHienTai_119?.layThongTin_119()

        Toast.makeText(this, "Thêm khoản chi thành công!", Toast.LENGTH_SHORT).show()
    }

    // Hàm xem chi tiết khoản chi tự đặt có hậu tố _119
    private fun xemChiTietKhoanChi_119() {
        val khoanChi_119 = khoanChiHienTai_119
        if (khoanChi_119 == null || !khoanChi_119.laSoTienHopLe_119()) {
            Toast.makeText(this, "Vui lòng thêm khoản chi hợp lệ trước khi xem chi tiết!", Toast.LENGTH_SHORT).show()
            return
        }

        val intent_119 = Intent(this, ChiTietChiTieuActivity::class.java).apply {
            putExtra(ChiTietChiTieuActivity.EXTRA_KHOAN_CHI_119, khoanChi_119)
        }
        startActivity(intent_119)
    }
}