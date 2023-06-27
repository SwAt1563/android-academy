package birzeit.edu.trainingcenter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseAdapter extends ArrayAdapter<String> {

    private List<String> courseList;
    private Set<String> selectedCourses;

    public CourseAdapter(Context context, List<String> courseList) {
        super(context, 0, courseList);
        this.courseList = courseList;
        this.selectedCourses = new HashSet<>();
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_course, parent, false);
        }

        CheckBox courseCheckBox = convertView.findViewById(R.id.courseCheckBox);
        String course = courseList.get(position);

        courseCheckBox.setText(course);
        courseCheckBox.setOnCheckedChangeListener(null);
        courseCheckBox.setChecked(selectedCourses.contains(course));
        courseCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedCourses.add(course);
            } else {
                selectedCourses.remove(course);
            }
        });

        return convertView;
    }

    public Set<String> getSelectedCourses() {
        return selectedCourses;
    }
}