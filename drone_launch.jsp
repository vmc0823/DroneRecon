<html>

  <body>

      <br />
      <h2 style="text-align:center;background-color:gray;color:white">DRONE RECON</h2>


      <form action="drone_sim.jsp" method="get" style="text-align:center;">
          <label for="area_id">Area ID:</label>
          <input type="text" name="area_id" id="area_id" required />
          <br /><br />

          <label for="colcount">Column Count:</label>
          <input type="text" name="colcount" id="colcount" required />
          <br /><br />

          <label for="rowcount">Row Count:</label>
          <input type="text" name="rowcount" id="rowcount" required />
          <br /><br />

          <button type="submit">Create Area Grid</button>
      </form>     
      
  </body>
</html>