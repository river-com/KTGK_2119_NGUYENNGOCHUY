package com.example.ktgk_2119_nguyenngochuy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2119_nguyenngochuy.databinding.ActivityChinhSuaChiTieuBinding
import java.util.Locale

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    private lateinit var binding_119: ActivityChinhSuaChiTieuBinding

    private var khoanChiTruyenSang_119: KhoanChi? = null

    companion object {
        const val EXTRA_KHOAN_CHI_119 = "EXTRA_KHOAN_CHI_119"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_119 = ActivityChinhSuaChiTieuBinding.inflate(layoutInflater)
        setContentView(binding_119.root)

        setSupportActionBar(binding_119.toolbar119)
        supportActionBar?.apply {
            title = "Nguyễn Ngọc Huy - 2415053122119 - Quản lý chi tiêu cá nhân"
            setDisplayHomeAsUpEnabled(true)
        }

        layDuLieuTruyenSang_119()
        dienDuLieuHienTai_119()

        binding_119.btnLuu119.setOnClickListener {
            luuChinhSua_119()
        }

        binding_119.btnHuy119.setOnClickListener {
            huyChinhSua_119()
        }
    }

    private fun layDuLieuTruyenSang_119() {
        @Suppress("DEPRECATION")
        khoanChiTruyenSang_119 = intent.getSerializableExtra(EXTRA_KHOAN_CHI_119) as? KhoanChi
    }

    private fun dienDuLieuHienTai_119() {
        val current_119 = khoanChiTruyenSang_119 ?: return
        binding_119.edtTenKhoanChi119.setText(current_119.tenKhoanChi_119)
        if (current_119.soTien_119 > 0) {
            binding_119.edtSoTien119.setText(String.format(Locale.getDefault(), "%.0f", current_119.soTien_119))
        }
        binding_119.edtGhiChu119.setText(current_119.ghiChu_119)
        binding_119.cbDaThanhToan119.isChecked = current_119.daThanhToan_119
    }

    private fun luuChinhSua_119() {
        val tenMoi_119 = binding_119.edtTenKhoanChi119.text.toString().trim()
        val soTienChuoiMoi_119 = binding_119.edtSoTien119.text.toString().trim()
        val ghiChuMoi_119 = binding_119.edtGhiChu119.text.toString().trim()
        val daThanhToanMoi_119 = binding_119.cbDaThanhToan119.isChecked

        if (tenMoi_119.isEmpty()) {
            Toast.makeText(this, "Tên khoản chi không được để trống!", Toast.LENGTH_SHORT).show()
            binding_119.edtTenKhoanChi119.requestFocus()
            return
        }

        if (soTienChuoiMoi_119.isEmpty()) {
            Toast.makeText(this, "Số tiền không được để trống!", Toast.LENGTH_SHORT).show()
            binding_119.edtSoTien119.requestFocus()
            return
        }

        val soTienChuyenDoi_119 = soTienChuoiMoi_119.toDoubleOrNull()
        if (soTienChuyenDoi_119 == null || soTienChuyenDoi_119 <= 0) {
            Toast.makeText(this, "Số tiền phải là số hợp lệ và lớn hơn 0!", Toast.LENGTH_SHORT).show()
            binding_119.edtSoTien119.requestFocus()
            return
        }

        val original_119 = khoanChiTruyenSang_119
        val khoanChiSuaMoi_119 = KhoanChi(
            maKhoanChi_119 = original_119?.maKhoanChi_119 ?: "KC119_0",
            tenKhoanChi_119 = tenMoi_119,
            soTien_119 = soTienChuyenDoi_119,
            ghiChu_119 = ghiChuMoi_119,
            daThanhToan_119 = daThanhToanMoi_119
        )

        val intentKetQua_119 = Intent().apply {
            putExtra(EXTRA_KHOAN_CHI_119, khoanChiSuaMoi_119)
        }

        setResult(Activity.RESULT_OK, intentKetQua_119)
        finish()
    }

    private fun huyChinhSua_119() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        huyChinhSua_119()
        return true
    }
}