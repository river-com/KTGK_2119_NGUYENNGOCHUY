package com.example.ktgk_2119_nguyenngochuy

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2119_nguyenngochuy.databinding.ActivityChiTietChiTieuBinding
import java.util.Locale

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var binding_119: ActivityChiTietChiTieuBinding

    private var tenKhoanChi_119: String = ""
    private var soTien_119: Double = 0.0
    private var ghiChu_119: String = ""
    private var daThanhToan_119: Boolean = false

    companion object {
        const val EXTRA_TEN_KHOAN_CHI_119 = "EXTRA_TEN_KHOAN_CHI_119"
        const val EXTRA_SO_TIEN_119 = "EXTRA_SO_TIEN_119"
        const val EXTRA_GHI_CHU_119 = "EXTRA_GHI_CHU_119"
        const val EXTRA_DA_THANH_TOAN_119 = "EXTRA_DA_THANH_TOAN_119"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_119 = ActivityChiTietChiTieuBinding.inflate(layoutInflater)
        setContentView(binding_119.root)

        setSupportActionBar(binding_119.toolbar119)
        supportActionBar?.apply {
            title = "Nguyễn Ngọc Huy - 2415053122119 - Quản lý chi tiêu cá nhân"
            setDisplayHomeAsUpEnabled(true)
        }

        layDuLieuIntent_119()
        hienThiThongTin_119()

        binding_119.btnQuayLai119.setOnClickListener {
            finish()
        }

        binding_119.btnChinhSua119.setOnClickListener {
            Toast.makeText(this, "Chức năng chỉnh sửa sẽ thực hiện ở Câu 3", Toast.LENGTH_SHORT).show()
        }
    }

    private fun layDuLieuIntent_119() {
        tenKhoanChi_119 = intent.getStringExtra(EXTRA_TEN_KHOAN_CHI_119) ?: ""
        soTien_119 = intent.getDoubleExtra(EXTRA_SO_TIEN_119, 0.0)
        ghiChu_119 = intent.getStringExtra(EXTRA_GHI_CHU_119) ?: ""
        daThanhToan_119 = intent.getBooleanExtra(EXTRA_DA_THANH_TOAN_119, false)

        // Kiểm tra tính hợp lệ của dữ liệu Intent
        if (tenKhoanChi_119.isBlank() || soTien_119 <= 0) {
            Toast.makeText(this, "Dữ liệu khoản chi truyền sang không hợp lệ!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun hienThiThongTin_119() {
        binding_119.tvTenKhoanChi119.text = tenKhoanChi_119
        binding_119.tvSoTien119.text = String.format(Locale.getDefault(), "%,.0f VNĐ", soTien_119)
        binding_119.tvGhiChu119.text = if (ghiChu_119.isNotBlank()) ghiChu_119 else "(Không có ghi chú)"
        binding_119.tvTrangThaiThanhToan119.text = if (daThanhToan_119) "Đã thanh toán" else "Chưa thanh toán"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}