package pollux.trialbee.com.pollux;

import android.content.Intent;

/**
 * Created by dauvid on 2015-02-26.
 */
public interface Callback {
    public void done(int requestCode, int resultCode, Intent data);
}
