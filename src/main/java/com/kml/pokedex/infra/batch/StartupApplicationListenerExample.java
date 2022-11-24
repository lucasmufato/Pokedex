package com.kml.pokedex.infra.batch;

import com.kml.pokedex.core.actions.LoadData;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupApplicationListenerExample implements
    ApplicationListener<ContextRefreshedEvent> {

  private static final Logger LOG = Logger.getLogger(StartupApplicationListenerExample.class.getName());
  @Autowired
  private LoadData loadData;

  @Override public void onApplicationEvent(ContextRefreshedEvent event) {
    LOG.info("Listened to application starting, loading data");
    loadData.invoke();
  }

}
