package hellindustries.wannabeahero.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.labo.kaji.fragmentanimations.MoveAnimation;

import hellindustries.wannabeahero.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnMainFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener{


    private Button accidentBtn;
    private Button mutationBtn;
    private Button bornBtn;
    private Button chooseBtn;

    private int selectedChoice;

    public static final int CAME_BY_ACCIDENT = 0;
    public static final int GENETIC_MUTATION = 1;
    public static final int BORN_WITH_THEM = 2;
    public static final int DURATION = 500;
    public static final int DEACTIVATED_BUTTON_ALPHA = 128;
    public static final int ACTIVATED_BUTTON_ALPHA = 255;


    private OnMainFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        accidentBtn = (Button) view.findViewById(R.id.accidentBtn);
        mutationBtn = (Button) view.findViewById(R.id.mutationBtn);
        bornBtn = (Button) view.findViewById(R.id.bornBtn);
        chooseBtn = (Button) view.findViewById(R.id.chooseBtn);

        accidentBtn.setOnClickListener(this);
        mutationBtn.setOnClickListener(this);
        bornBtn.setOnClickListener(this);
        chooseBtn.setOnClickListener(this);

        chooseBtn.setEnabled(false);
        chooseBtn.getBackground().setAlpha(DEACTIVATED_BUTTON_ALPHA);


        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMainFragmentInteractionListener) {
            mListener = (OnMainFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(enter) {
            return MoveAnimation.create(MoveAnimation.RIGHT, enter, DURATION);
        } else {
            return MoveAnimation.create(MoveAnimation.LEFT, enter, DURATION);
        }
    }

    @Override
    public void onClick(View view) {
        chooseBtn.setEnabled(true);
        chooseBtn.getBackground().setAlpha(ACTIVATED_BUTTON_ALPHA);

        Button btn = (Button) view;

        if (btn == accidentBtn) {

            btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lightning,0,R.drawable.item_selected,0);
            mutationBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.atomic,0,R.drawable.item_unselected,0);
            bornBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rocket,0,R.drawable.item_unselected,0);
            selectedChoice = CAME_BY_ACCIDENT;

        } else if (btn == mutationBtn) {

            btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.atomic,0,R.drawable.item_selected,0);
            accidentBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lightning,0,R.drawable.item_unselected,0);
            bornBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rocket,0,R.drawable.item_unselected,0);
            selectedChoice = GENETIC_MUTATION;

        } else if  (btn == bornBtn) {

            btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rocket,0,R.drawable.item_selected,0);
            accidentBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.lightning,0,R.drawable.item_unselected,0);
            mutationBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.atomic,0,R.drawable.item_unselected,0);
            selectedChoice = BORN_WITH_THEM;

        } else if (btn == chooseBtn){
            mListener.onMainFragmentInteraction(selectedChoice);
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnMainFragmentInteractionListener {
        void onMainFragmentInteraction(int powerAcquisitionChoice);
    }
}
