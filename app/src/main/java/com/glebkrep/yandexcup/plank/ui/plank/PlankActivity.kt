package com.glebkrep.yandexcup.plank.ui.plank

import android.os.Bundle
import android.view.SurfaceView
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.glebkrep.yandexcup.plank.R
import com.glebkrep.yandexcup.plank.poseDetection.camera.CameraSource
import com.glebkrep.yandexcup.plank.poseDetection.data.Device
import com.glebkrep.yandexcup.plank.poseDetection.ml.ModelType
import com.glebkrep.yandexcup.plank.poseDetection.ml.MoveNet
import com.glebkrep.yandexcup.plank.poseDetection.ml.PoseClassifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlankActivity : AppCompatActivity() {

    private lateinit var surfaceView: SurfaceView

    private var device = Device.CPU

    private lateinit var tvScore: TextView
    private lateinit var tvIsPlank: TextView
    private lateinit var btnStop: Button

    private lateinit var tvClassificationValue1: TextView
    private lateinit var tvClassificationValue2: TextView
    private lateinit var tvClassificationValue3: TextView
    private var cameraSource: CameraSource? = null

    private val viewModel by viewModels<PlankActivityVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        // keep screen on while app is running
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        tvScore = findViewById(R.id.tvScore)
        surfaceView = findViewById(R.id.surfaceView)
        tvIsPlank = findViewById(R.id.tvIsPlank)
        btnStop = findViewById(R.id.btnStop)
        tvClassificationValue1 = findViewById(R.id.tvClassificationValue1)
        tvClassificationValue2 = findViewById(R.id.tvClassificationValue2)
        tvClassificationValue3 = findViewById(R.id.tvClassificationValue3)
        tvClassificationValue1.visibility = View.VISIBLE
        tvClassificationValue2.visibility = View.VISIBLE
        tvClassificationValue3.visibility = View.VISIBLE
        isPoseClassifier()

        btnStop.setOnClickListener {
            viewModel.plankEnd(System.currentTimeMillis())
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        openCamera()
    }

    override fun onResume() {
        cameraSource?.resume()
        super.onResume()
    }

    override fun onPause() {
        cameraSource?.close()
        cameraSource = null
        super.onPause()
    }

    // open camera
    private fun openCamera() {
        if (cameraSource == null) {
            cameraSource =
                CameraSource(surfaceView, object : CameraSource.CameraSourceListener {
                    override fun onFPSListener(fps: Int) {
                    }

                    override fun onDetectedInfo(
                        personScore: Float?,
                        poseLabels: List<Pair<String, Float>>?
                    ) {
                        tvScore.text = getString(R.string.tfe_pe_tv_score, personScore ?: 0f)
                        poseLabels?.sortedByDescending { it.second }?.let {
                            tvClassificationValue1.text = getString(
                                R.string.tfe_pe_tv_classification_value,
                                convertPoseLabels(if (it.isNotEmpty()) it[0] else null)
                            )
                            tvClassificationValue2.text = getString(
                                R.string.tfe_pe_tv_classification_value,
                                convertPoseLabels(if (it.size >= 2) it[1] else null)
                            )
                            tvClassificationValue3.text = getString(
                                R.string.tfe_pe_tv_classification_value,
                                convertPoseLabels(if (it.size >= 3) it[2] else null)
                            )
                        }
                    }

                    override fun plankStarted(timestamp: Long) {
                        tvIsPlank.text = "Вы в планке!"
                        viewModel.plankStart(timestamp)
                    }

                    override fun plankEnded(timestamp: Long) {
                        tvIsPlank.text = "-"
                        viewModel.plankEnd(timestamp)
                    }

                }).apply {
                    prepareCamera()
                }
            isPoseClassifier()
            lifecycleScope.launch(Dispatchers.Main) {
                cameraSource?.initCamera()
            }
        }
        createPoseEstimator()
    }

    private fun convertPoseLabels(pair: Pair<String, Float>?): String {
        if (pair == null) return "empty"
        return "${pair.first} (${String.format("%.2f", pair.second)})"
    }

    private fun isPoseClassifier() {
        cameraSource?.setClassifier(PoseClassifier.create(this))
    }

    private fun createPoseEstimator() {
        val poseDetector = MoveNet.create(this, device, ModelType.Thunder)
        cameraSource?.setDetector(poseDetector)
    }

}