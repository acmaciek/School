//
// GUI app to Analyze Chicago crime data, using SQL and ADO.NET
//
// Maciej Girek
// U. of Illinois, Chicago
// CS 341, Spring 2018
// Project #07
//
using System;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;


namespace ChicagoCrime
{
  public partial class Form1 : Form
  {
     public Form1()
    {
      InitializeComponent();
    }

    private void Form1_Load(object sender, EventArgs e)
    {
      this.clearForm();
    }

    private bool fileExists(string filename)
    {
      if (!System.IO.File.Exists(filename))
      {
        string msg = string.Format("Input file not found: '{0}'",
          filename);

        MessageBox.Show(msg);
        return false;
      }

      // exists!
      return true;
    }

    private void clearForm()
    {
      this.chart.Series.Clear();
      this.chart.Titles.Clear();
      this.chart.Legends.Clear();
    }

    private void cmdByYear_Click(object sender, EventArgs e)
    {
      //
      // Check to make sure database filename in text box actually exists:
      //
      string filename = this.txtFilename.Text;

      if (!fileExists(filename))
        return;

      this.Cursor = Cursors.WaitCursor;

      clearForm();

            //
            // Retrieve data from database:
            //
      string version, connectionInfo;
      SqlConnection db;
      version = "MSSQLLocalDB";
      filename = "CrimeDB.mdf";
      connectionInfo = String.Format(@"
      Data Source=(LocalDB)\{0};AttachDbFilename=|DataDirectory|\{1};Integrated Security=True;", version, filename);
      db = new SqlConnection(connectionInfo);
      db.Open();
            //MessageBox.Show(db.State.ToString());
      string sql, msg;
      SqlCommand cmd;
      object result;
      sql = @"
            SELECT * FROM Crimes";

            //sql = string.Format(@"SELECT Year,Count(*) As Total FROM Crimes GROUP BY Year");
      cmd = new SqlCommand();
      cmd.Connection = db;
      SqlDataAdapter adapter = new SqlDataAdapter(cmd);
      DataSet ds = new DataSet();
      cmd.CommandText = sql;
      adapter.Fill(ds);
            Console.WriteLine(Convert.ToString(adapter.Fill(ds)));

            result = cmd.ExecuteScalar();
      MessageBox.Show(Convert.ToString(result));
            
            //
            // now graph as a line chart:
            //
            this.chart.Titles.Add("Total # of Crimes Per Year");

      var series = this.chart.Series.Add("total # of crimes");

      series.ChartType = SeriesChartType.Line;


      var legend = new Legend();
      legend.Docking = Docking.Top;
      this.chart.Legends.Add(legend);

      // 
      // done:
      //
      this.Cursor = Cursors.Default;
      db.Close();
        }

    private void cmdArrested_Click(object sender, EventArgs e)
    {
      //
      // Check to make sure database filename in text box actually exists:
      //
      string filename = this.txtFilename.Text;

      if (!fileExists(filename))
        return;

      this.Cursor = Cursors.WaitCursor;

      clearForm();

  
      string version, connectionInfo;
      SqlConnection db;
      version = "MSSQLLocalDB";
      filename = "CrimeDB.mdf";
      connectionInfo = String.Format(@"
      Data Source=(LocalDB)\{0};AttachDbFilename=|DataDirectory|\{1};Integrated Security=True;", version, filename);
      db = new SqlConnection(connectionInfo);
      db.Open();
            //MessageBox.Show(db.State.ToString());
      string sql,sql2, msg;
      SqlCommand cmd,cmd2;
      object result;
      sql = @"
      SELECT Year,Count(*) AS Total FROM Crimes GROUP BY Year Order By Year";
      sql2 = @"
      SELECT Year,Count(*) AS Total FROM Crimes WHERE Arrested = 1 GROUP BY Year Order By Year";
      cmd = new SqlCommand();
      cmd2 = new SqlCommand();
      cmd.Connection = db;
      cmd2.Connection = db;
      SqlDataAdapter adapter = new SqlDataAdapter(cmd);
      SqlDataAdapter adapter2 = new SqlDataAdapter(cmd2);
      DataSet ds = new DataSet();
      DataSet ds2 = new DataSet();
      cmd.CommandText = sql;
      cmd2.CommandText = sql2;
      adapter.Fill(ds);
      adapter2.Fill(ds2);
  
      List<int> X = new List<int>();
      List<int> Y1 = new List<int>();
      List<int> Y2 = new List<int>();

      foreach (DataRow row in ds.Tables["TABLE"].Rows)
      {
        X.Add(Convert.ToInt32(row["Year"]));
        Y1.Add(Convert.ToInt32(row["Total"]));
      }
            
      foreach (DataRow row in ds2.Tables["TABLE"].Rows) { 
         Y2.Add(Convert.ToInt32(row["Total"]));
      }

            //
            // now graph as a line chart:
            //
      this.chart.Titles.Add("Total # of Crimes Per Year vs. Number Arrested");

      var series = this.chart.Series.Add("total # of crimes");

      series.ChartType = SeriesChartType.Line;

      for (int i = 0; i < X.Count; ++i)
      {
        series.Points.AddXY(X[i], Y1[i]);
      }

      var series2 = this.chart.Series.Add("# arrested");

      series2.ChartType = SeriesChartType.Line;

      for (int i = 0; i < X.Count; ++i)
      {
        series2.Points.AddXY(X[i], Y2[i]);
      }

      var legend = new Legend();
      legend.Docking = Docking.Top;
      this.chart.Legends.Add(legend);

      //
      // done:
      //
      this.Cursor = Cursors.Default;
            db.Close();
        }

    private void cmdOneArea_Click(object sender, EventArgs e)
    {
      //
      // Check to make sure database filename in text box actually exists:
      //
      string filename = this.txtFilename.Text;

      if (!fileExists(filename))
        return;

      this.Cursor = Cursors.WaitCursor;

      clearForm();

   
      string version, connectionInfo;
      SqlConnection db;
      version = "MSSQLLocalDB";
      filename = "CrimeDB.mdf";
      connectionInfo = String.Format(@"
      Data Source=(LocalDB)\{0};AttachDbFilename=|DataDirectory|\{1};Integrated Security=True;", version, filename);
      db = new SqlConnection(connectionInfo);
      db.Open();
      //MessageBox.Show(db.State.ToString());
      string sql,sql1, sql2, msg;
      SqlCommand cmd,cmd1, cmd2;
      object result;
      sql = @"
      SELECT Year,Count(*) AS Total FROM Crimes GROUP BY Year Order By Year";
      sql1 = string.Format(@"
      SELECT Area FROM Areas WHERE AreaName = '{0}';", this.txtArea.Text);
      cmd1 = new SqlCommand();
      cmd1.Connection = db;
      cmd1.CommandText = sql1;
      object result1 = cmd1.ExecuteScalar();
      //MessageBox.Show(Convert.ToString(result1));
      int number = Convert.ToInt32(result1);
      sql2 = string.Format(@"
      SELECT Year,Count(*) AS Total FROM Crimes WHERE Area = {0} GROUP BY Year Order By Year", number);

      cmd = new SqlCommand();
      cmd2 = new SqlCommand();
      cmd.Connection = db;
      cmd2.Connection = db;
      SqlDataAdapter adapter = new SqlDataAdapter(cmd);
      SqlDataAdapter adapter2 = new SqlDataAdapter(cmd2);
      DataSet ds = new DataSet();
      DataSet ds2 = new DataSet();
      cmd.CommandText = sql;
      cmd2.CommandText = sql2;
      adapter.Fill(ds);
      adapter2.Fill(ds2);
            




            //
            // ????????????????
            //
            // NOTE: you might be able to do this with one SQL query,
            // but probably easier to just execute 2 queries: one to
            // get the total counts, and then another to get the counts
            // for the area specified by the user.  You may assume the
            // area name entered by the user exists (though FYI using a 
            // different type of join yields the necessary counts of 0
            // for plotting, and then it always works no matter what the
            // user enters).
            //





            //
            // Build a set of (x,y) points for plotting:
            //
      List<int> X = new List<int>();
      List<int> Y1 = new List<int>();
      List<int> Y2 = new List<int>();

      foreach (DataRow row in ds.Tables["TABLE"].Rows)
      {
        X.Add(Convert.ToInt32(row["Year"]));
        Y1.Add(Convert.ToInt32(row["Total"]));
      }

      foreach (DataRow row in ds2.Tables["TABLE"].Rows)
      {
        Y2.Add(Convert.ToInt32(row["Total"]));
      }

      //
      // now graph as a line chart:
      //
      this.chart.Titles.Add("Total # of Crimes Per Year vs. Particular Area");

      var series = this.chart.Series.Add("total # of crimes");

      series.ChartType = SeriesChartType.Line;

      for (int i = 0; i < X.Count; ++i)
      {
        series.Points.AddXY(X[i], Y1[i]);
      }

      var series2 = this.chart.Series.Add("# in this area");

      series2.ChartType = SeriesChartType.Line;

      for (int i = 0; i < X.Count; ++i)
      {
        series2.Points.AddXY(X[i], Y2[i]);
      }

      var legend = new Legend();
      legend.Docking = Docking.Top;
      this.chart.Legends.Add(legend);

      //
      // done:
      //
      this.Cursor = Cursors.Default;
    }

    private void cmdChicagoAreas_Click(object sender, EventArgs e)
    {
      //
      // Check to make sure database filename in text box actually exists:
      //
      string filename = this.txtFilename.Text;

      if (!fileExists(filename))
        return;

      this.Cursor = Cursors.WaitCursor;

      clearForm();

            //
            // Retrieve data from database:
            //
            //
            // ????????????????
            //

      string version, connectionInfo;
      SqlConnection db;
      version = "MSSQLLocalDB";
      filename = "CrimeDB.mdf";
      connectionInfo = String.Format(@"Data Source=(LocalDB)\{0};AttachDbFilename=|DataDirectory|\{1};Integrated Security=True;", version, filename);
      db = new SqlConnection(connectionInfo);
      db.Open();
      string sql;
      SqlCommand cmd;
      sql = @"SELECT Area, Count(*) AS 'Total' FROM Crimes WHERE Area > 0 GROUP BY Area ORDER BY Area";
      cmd = new SqlCommand();
      DataSet ds = new DataSet();
      cmd.CommandText = sql;
      SqlDataAdapter adapter = new SqlDataAdapter(cmd);
      adapter.Fill(ds);
      db.Close();

            //
            // Build a set of (x,y) points for plotting:
            //
      List<int> X = new List<int>();
      List<int> Y = new List<int>();

      foreach (DataRow row in ds.Tables["TABLE"].Rows)
      {
        X.Add(Convert.ToInt32(row["Area"]));
        Y.Add(Convert.ToInt32(row["Total"]));
      }

      //
      // now graph as a line chart:
      //
      this.chart.Titles.Add("Total # of Crimes in each Chicago Area");

      var series = this.chart.Series.Add("total # of crimes");

      series.ChartType = SeriesChartType.Line;

      for (int i = 0; i < X.Count; ++i)
      {
        series.Points.AddXY(X[i], Y[i]);
      }

      var legend = new Legend();
      legend.Docking = Docking.Top;
      this.chart.Legends.Add(legend);

      //
      // done:
      //
      this.Cursor = Cursors.Default;
    }

        private void chart_Click(object sender, EventArgs e)
        {
           
        }

        private void txtArea_TextChanged(object sender, EventArgs e)
        {
            // MessageBox.Show(sender.ToString());
        }

        private void txtFilename_TextChanged(object sender, EventArgs e)
        {

        }
    }//class
}//namespace
