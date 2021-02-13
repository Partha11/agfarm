package com.techmave.agfarm.dialog;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.techmave.agfarm.R;
import com.techmave.agfarm.adapter.SpinnerAdapter;
import com.techmave.agfarm.databinding.DialogDatePickerBinding;
import com.techmave.agfarm.model.Crop;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class DatePickerDialog extends DialogFragment implements View.OnClickListener {

    private DialogDatePickerBinding binding;
    private List<Crop> crops;

    private DateListener listener = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DialogDatePickerBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    @Override
    public void onResume() {

        super.onResume();

        Window window = Objects.requireNonNull(getDialog()).getWindow();
        Point size = new Point();

        Display display = Objects.requireNonNull(window).getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;

        window.setLayout((int) (width * 0.95), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER_HORIZONTAL);
    }

    private void initialize() {

        SpinnerAdapter adapter = new SpinnerAdapter(requireContext(), R.layout.spinner_dropdown, crops);
        binding.cropSpinner.setAdapter(adapter);

        binding.confirmButton.setOnClickListener(this);
        binding.cancelButton.setOnClickListener(this);
    }

    public void setCrops(List<Crop> crops) {

        this.crops = crops;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.confirm_button) {

            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.DAY_OF_MONTH, binding.datePicker.getDayOfMonth());
            calendar.set(Calendar.MONTH, binding.datePicker.getMonth());
            calendar.set(Calendar.YEAR, binding.datePicker.getYear());

            if (listener != null) {

                listener.onDatePicked(crops.get(binding.cropSpinner.getSelectedItemPosition()), calendar.getTimeInMillis());
            }
        }

        dismiss();
    }

    public void setListener(DateListener listener) {

        this.listener = listener;
    }

    public interface DateListener {

        void onDatePicked(Crop crop, Long millis);
    }
}
