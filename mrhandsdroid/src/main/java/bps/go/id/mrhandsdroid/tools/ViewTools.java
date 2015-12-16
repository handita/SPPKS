package bps.go.id.mrhandsdroid.tools;

/**
 * Created by handi_000 on 6/30/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

import bps.go.id.mrhandsdroid.R;

public class ViewTools {
    private static final int ORTU = 6;

    private static final int ISTRI = 2;

    public static RadioButton getChildAt(RadioGroup group, int index)
            throws Exception {
        int count = group.getChildCount();
        List<RadioButton> list = new ArrayList<RadioButton>();
        int i = 0;
        while (i < count) {
            View view = group.getChildAt(i);
            if (view instanceof RadioButton) {
                list.add((RadioButton) view);
            }
            i++;
        }
        if (list.size() < index + 1)
            throw new Exception(
                    "Maaf index lebih besar dari banyaknya radiobutton");
        return list.get(index);
    }

    public static void disableControl(ViewGroup group) {
        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = group.getChildAt(i);
            v.setEnabled(false);
            if (v instanceof RadioButton) {
                ((RadioButton) v).setChecked(false);
            } else if (v instanceof EditText) {
                ((EditText) v).setText("");

                v.setBackgroundResource(R.drawable.edittext_disabled);
            }

        }
    }

    public static void disableControls(Object... group) {
        int jumlahViewGroup = group.length;
        for (int i = 0; i < jumlahViewGroup; i++) {
            Object o = group[i];
            if (o instanceof RadioGroup) {
                ((RadioGroup) o).clearCheck();
                ((RadioGroup) o).setEnabled(false);
                disableControl((ViewGroup) group[i]);
            } else if (o instanceof EditText) {
                EditText t = (EditText) o;
                t.setEnabled(false);
                t.setText("");
                t.setBackgroundResource(R.drawable.edittext_disabled);

            } else if (o instanceof Spinner) {
                Spinner s = (Spinner) o;
                s.setEnabled(false);
                s.setSelection(0);

            }
        }
    }

    public static void enableControl(ViewGroup group) {
        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = group.getChildAt(i);
            v.setEnabled(true);

        }
    }

    public static void enableControls(Object... group) {
        int jumlahViewGroup = group.length;
        for (int i = 0; i < jumlahViewGroup; i++) {
            Object o = group[i];
            if (o instanceof Spinner) {
                Spinner s = (Spinner) o;
                s.setEnabled(true);
            } else if (o instanceof ViewGroup) {
                ((ViewGroup) o).setEnabled(true);
                enableControl((ViewGroup) group[i]);
            } else if (o instanceof EditText) {
                EditText t = (EditText) o;
                t.setEnabled(true);
                t.setBackgroundResource(android.R.drawable.editbox_background_normal);

            }

        }
    }

    public static boolean inArray(int[] array, int value) {
        boolean result = false;
        for (int i : array) {
            if (i == value) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Fungsi untuk mendapatkan indeks dari radiobutton dalam sebuah radiogroup
     * jika di dalamnya ada element yang lain diacuhkan
     *
     * @param group
     * @param child
     * @return
     */
    public static int indexOfChild(RadioGroup group, View child) {
        int count = group.getChildCount();

        int i = 0;
        int indexSelected = 0;
        while (i < count) {
            View view = group.getChildAt(i);
            if (view instanceof RadioButton) {
                if (view == child) {
                    return indexSelected;
                }
                indexSelected++;
            }
            i++;
        }
        return -1;
    }

    /**
     * Start activity dengan top activity di clear
     *
     * @param activity
     * @param tujuan
     */
    public static void startActivity(Activity activity, Class<?> tujuan) {
        Intent nextScreen = new Intent(activity, tujuan);
        nextScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(nextScreen);
        activity.finish();
    }
}
