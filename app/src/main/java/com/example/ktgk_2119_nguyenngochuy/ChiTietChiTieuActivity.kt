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

    // Lớp KhoanChi làm đối tượng dữ liệu duy nhất
    private var khoanChi_119: KhoanChi? = null

    private lateinit var chinhSuaLauncher_119: ActivityResultLauncher<Intent>

    companion object {
        const val EXTRA_KHOAN_CHI_119 = "EXTRA_KHOAN_CHI_119"
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

        // Khởi tạo Activity Result Launcher
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
                @Suppress("DEPRECATION")
                val khoanChiDaSua_119 = data_119.getSerializableExtra(ChinhSuaChiTieuActivity.EXTRA_KHOAN_CHI_119) as? KhoanChi

                if (khoanChiDaSua_119 != null && khoanChiDaSua_119.tenKhoanChi_119.isNotBlank() && khoanChiDaSua_119.laSoTienHopLe_119()) {
                    // Cập nhật thông tin khoản chi mới giữ nguyên mã khoản chi cũ
                    khoanChi_119?.apply {
                        tenKhoanChi_119 = khoanChiDaSua_119.tenKhoanChi_119
                        soTien_119 = khoanChiDaSua_119.soTien_119
                        ghiChu_119 = khoanChiDaSua_119.ghiChu_119
                        daThanhToan_119 = khoanChiDaSua_119.daThanhToan_119
                    }

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
        val target_119 = khoanChi_119 ?: return
        val intent_119 = Intent(this, ChinhSuaChiTieuActivity::class.java).apply {
            putExtra(ChinhSuaChiTieuActivity.EXTRA_KHOAN_CHI_119, target_119)
        }
        chinhSuaLauncher_119.launch(intent_119)
    }

    private fun layDuLieuIntent_119() {
        @Suppress("DEPRECATION")
        khoanChi_119 = intent.getSerializableExtra(EXTRA_KHOAN_CHI_119) as? KhoanChi

        val current_119 = khoanChi_119
        if (current_119 == null || current_119.tenKhoanChi_119.isBlank() || !current_119.laSoTienHopLe_119()) {
            Toast.makeText(this, "Dữ liệu khoản chi không hợp lệ!", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun hienThiThongTin_119() {
        val current_119 = khoanChi_119 ?: return
        binding_119.tvTenKhoanChi119.text = current_119.tenKhoanChi_119
        binding_119.tvSoTien119.text = String.format(Locale.getDefault(), "%,.0f VNĐ", current_119.soTien_119)
        binding_119.tvGhiChu119.text = if (current_119.ghiChu_119.isNotBlank()) current_119.ghiChu_119 else "(Không có ghi chú)"
        binding_119.tvTrangThaiThanhToan119.text = if (current_119.daThanhToan_119) "Đã thanh toán" else "Chưa thanh toán"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}