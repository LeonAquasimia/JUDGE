package engine.entity.component;

import engine.entity.Entity;

/**
 * Created by Admin on 2/8/2015.
 */
public class CamMoveTest extends Component
{
    public void update(float delta, Entity entity)
    {
        entity.transform().position(entity.transform().position().add(delta,0,0));
    }
}
