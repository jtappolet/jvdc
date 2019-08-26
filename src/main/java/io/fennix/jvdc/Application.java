package io.fennix.jvdc;

import com.blade.Blade;

public class Application {

    public static void main(String[] args) {

        Blade.of()
  .get("/basic-routes-example", ctx -> ctx.text("GET called"))
  .post("/basic-routes-example", ctx -> ctx.text("POST called"))
  .put("/basic-routes-example", ctx -> ctx.text("PUT called"))
  .delete("/basic-routes-example", ctx -> ctx.text("DELETE called"))
  .start(Application.class, args);

    }

}
