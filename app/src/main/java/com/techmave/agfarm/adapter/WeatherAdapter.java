package com.techmave.agfarm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techmave.agfarm.R;
import com.techmave.agfarm.databinding.ModelWeatherBinding;
import com.techmave.agfarm.model.Daily;
import com.techmave.agfarm.utility.Utility;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Daily> items;
    private Context context;

    public WeatherAdapter(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_weather, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Daily item = items.get(position);

        if (item != null) {

            holder.binding.dayName.setText(Utility.getDayStringFromTimestamp(item.getDt()));
            holder.binding.pressureText.setText("Pressure: " + item.getPressure().toString());
            holder.binding.humidityText.setText("Humidity: " + item.getHumidity().toString());
            holder.binding.temperatureText.setText(context.getString(R.string.temperature_text_dynamic, Utility.roundToTwoDecimalPoints(item.getTemp().getMax())));
        }
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void setItems(List<Daily> items) {

        this.items = items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ModelWeatherBinding binding;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            binding = ModelWeatherBinding.bind(itemView);
        }
    }
}
