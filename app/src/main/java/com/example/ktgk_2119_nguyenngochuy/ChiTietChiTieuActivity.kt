package com.example.ktgk_2119_nguyenngochuy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2119_nguyenngochuy.databinding.ActivityChiTietChiTieuBinding
import java.util.Locale

class ChiTietChiTieuActivity : AppCompatActivity() {

    private lateinit var binding_119: ActivityChiTietChiTieuBinding

    private var tenKhoanChi_119: String = ""
    private var soTien_119: Double = 0.0
    private var ghiChu_119: String = ""
    private var daThanhToan_119: Boolean = false

    private lateinit var chinhSuaLauncher_119: ActivityResultLauncher<Intent>

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

        // Đăng ký Activity Result Launcher theo Activity Result API
        khoiTaoChinhSuaLauncher_119()

        layDuLieuIntent_119()
        hienThiThongTin_119()

        binding_119.btnQuayLai119.setOnClickListener {
            finish()
        }

        binding_119.btnChinhSua119.setOnClickListener {
            moManHinhChinhSua_119()
        }
    }

    private fun khoiTaoChinhSuaLauncher_119() {
        chinhSuaLauncher_119 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result_119 ->
            if (result_119.resultCode == Activity.RESULT_OK && result_119.data != null) {
                val data_119 = result_119.data!!
                val tenMoi_119 = data_119.getStringExtra(ChinhSuaChiTieuActivity.EXTRA_TEN_KHOAN_CHI_119) ?: ""
                val soTienMoi_119 = data_119.getDoubleExtra(ChinhSuaChiTieuActivity.EXTRA_SO_TIEN_119, 0.0)
                val ghiChuMoi_119 = data_119.getStringExtra(ChinhSuaChiTieuActivity.EXTRA_GHI_CHU_119) ?: ""

                if (tenMoi_119.isNotBlank() && soTienMoi_119 > 0) {
                    tenKhoanChi_119 = tenMoi_119
                    soTien_119 = soTienMoi_119
                    ghiChu_119 = ghiChuMoi_119

                    hienThiThongTin_119()
                    Toast.makeText(this, "Cập nhật khoản chi thành công!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Dữ liệu trả về không hợp lệ!", Toast.LENGTH_SHORT).show()
                }
            } else if (result_119.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Đã hủy chỉnh sửa khoản chi", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun moManHinhChinhSua_119() {
        val intent_119 = Intent(this, ChinhSuaChiTieuActivity::class.java).apply {
            putExtra(ChinhSuaChiTieuActivity.EXTRA_TEN_KHOAN_CHI_119, tenKhoanChi_119)
            putExtra(ChinhSuaChiTieuActivity.EXTRA_SO_TIEN_119, soTien_119)
            putExtra(ChinhSuaChiTieuActivity.EXTRA_GHI_CHU_119, ghiChu_119)
        }
        chinhSuaLauncher_119.launch(intent_119)
    }

    private fun layDuLieuIntent_119() {
        tenKhoanChi_119 = intent.getStringExtra(EXTRA_TEN_KHOAN_CHI_119) ?: ""
        soTien_119 = intent.getDoubleExtra(EXTRA_SO_TIEN_119, 0.0)
        ghiChu_119 = intent.getStringExtra(EXTRA_GHI_CHU_119) ?: ""
        daThanhToan_119 = intent.getBooleanExtra(EXTRA_DA_THANH_TOAN_119, false)

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