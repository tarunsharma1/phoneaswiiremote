using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using email.Resources;
using Microsoft.Phone.Tasks;
using Microsoft.Xna.Framework;
using Microsoft.Devices.Sensors;
using WebSocket4Net;

namespace email
{
    public partial class MainPage : PhoneApplicationPage
    {
        Accelerometer accelerometer;
//provied your IP address here...leave ws as it is..it stands for websocket..also provide any port number like 8080
        WebSocket websoc = new WebSocket("ws://192.168.1.3:8080","");
        // Constructor
        public MainPage()
        {
            InitializeComponent();

            // Sample code to localize the ApplicationBar
            //BuildLocalizedApplicationBar();
        }

      

      
//get accelerometer values
        private void Accelerometer_Click(object sender, RoutedEventArgs e)
        {
            accelerometer = new Accelerometer();
            accelerometer.TimeBetweenUpdates = TimeSpan.FromMilliseconds(20);
            accelerometer.CurrentValueChanged += new EventHandler<SensorReadingEventArgs<AccelerometerReading>>(accelerometer_valuechanged);
            accelerometer.Start();
        }
        private void accelerometer_valuechanged(object sender,SensorReadingEventArgs<AccelerometerReading> e)
        {
            Dispatcher.BeginInvoke(() => UpdateUI(e.SensorReading));
        
        }
        private void UpdateUI(AccelerometerReading accreading)
        {
            Vector3 acc = accreading.Acceleration;
            X.Text = acc.X.ToString("0.00");
            Y.Text = acc.Y.ToString("0.00");
            Z.Text = acc.Z.ToString("0.00");
//send w,a,s or d according to accelerometer readings to server
            if (acc.Z < -0.55)
            {
                websoc.Send("w");

            }
            else if (acc.Z > 0.25)
            {
                websoc.Send("s");
            }
            else if (acc.Y > 0.30)
            {
                websoc.Send("a");
            }
            else if (acc.Y < -0.30)
            {
                websoc.Send("d");
            }
            else
                websoc.Send("p");
        }
//open the socket
        private void socket_Click(object sender, RoutedEventArgs e)
        {
           
            websoc.Opened += new EventHandler(websocket_Opened);
            websoc.Open();
        }
        private void websocket_Opened(object sender, EventArgs e)
        {
            websoc.Send("Hello World!");
        }

        private void close_socket_Click(object sender, RoutedEventArgs e)
        {
            websoc.Close();
        }
     

        // Sample code for building a localized ApplicationBar
        //private void BuildLocalizedApplicationBar()
        //{
        //    // Set the page's ApplicationBar to a new instance of ApplicationBar.
        //    ApplicationBar = new ApplicationBar();

        //    // Create a new button and set the text value to the localized string from AppResources.
        //    ApplicationBarIconButton appBarButton = new ApplicationBarIconButton(new Uri("/Assets/AppBar/appbar.add.rest.png", UriKind.Relative));
        //    appBarButton.Text = AppResources.AppBarButtonText;
        //    ApplicationBar.Buttons.Add(appBarButton);

        //    // Create a new menu item with the localized string from AppResources.
        //    ApplicationBarMenuItem appBarMenuItem = new ApplicationBarMenuItem(AppResources.AppBarMenuItemText);
        //    ApplicationBar.MenuItems.Add(appBarMenuItem);
        //}
    }
}