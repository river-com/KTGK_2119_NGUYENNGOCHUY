package com.example.ktgk_2119_nguyenngochuy

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktgk_2119_nguyenngochuy.databinding.ActivityChinhSuaChiTieuBinding

class ChinhSuaChiTieuActivity : AppCompatActivity() {

    private lateinit var binding_119: ActivityChinhSuaChiTieuBinding

    private var tenKhoanChi_119: String = ""
    private var soTien_119: Double = 0.0
    private var ghiChu_119: String = ""

    companion object {
        const val EXTRA_TEN_KHOAN_CHI_119 = "EXTRA_TEN_KHOAN_CHI_119"
        const val EXTRA_SO_TIEN_119 = "EXTRA_SO_TIEN_119"
        const val EXTRA_GHI_CHU_119 = "EXTRA_GHI_CHU_119"
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
        tenKhoanChi_119 = intent.getStringExtra(EXTRA_TEN_KHOAN_CHI_119) ?: ""
        soTien_119 = intent.getDoubleExtra(EXTRA_SO_TIEN_119, 0.0)
        ghiChu_119 = intent.getStringExtra(EXTRA_GHI_CHU_119) ?: ""
    }

    private fun dienDuLieuHienTai_119() {
        binding_119.edtTenKhoanChi119.setText(tenKhoanChi_119)
        if (soTien_119 > 0) {
            binding_119.edtSoTien119.setText(String.format("%.0f", soTien_119))
        }
        binding_119.edtGhiChu119.setText(ghiChu_119)
    }

    private fun luuChinhSua_119() {
        val tenMoi_119 = binding_119.edtTenKhoanChi119.text.toString().trim()
        val soTienChuoiMoi_119 = binding_119.edtSoTien119.text.toString().trim()
        val ghiChuMoi_119 = binding_119.edtGhiChu119.text.toString().trim()

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

        val intentKetQua_119 = Intent().apply {
            putExtra(EXTRA_TEN_KHOAN_CHI_119, tenMoi_119)
            putExtra(EXTRA_SO_TIEN_119, soTienChuyenDoi_119)
            putExtra(EXTRA_GHI_CHU_119, ghiChuMoi_119)
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