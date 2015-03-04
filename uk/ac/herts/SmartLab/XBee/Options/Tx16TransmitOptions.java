package uk.ac.herts.SmartLab.XBee.Options;

public class Tx16TransmitOptions extends OptionsBase
    {
        //0x01 - Disable retries and route repair
        //0x02 - Force a long header to precede this packet
        //0x04 - Disable Sending of long header
        //0x08 - Invoke Traceroute
        public boolean GetForceLongHeader()
        {
            if ((this.value & 0x02) == 0x02)
                return true;
            else return false;
        }

        public void SetForceLongHeader(boolean status)
        {
            if (status)
                this.value = (byte)(this.value | 0x02);
            else
                this.value = (byte)(this.value & 0xFD);
        }

        public boolean GetDisableLongHeader()
        {
            if ((this.value & 0x04) == 0x04)
                return true;
            else return false;
        }

        public void SetDisableLongHeader(boolean status)
        {
            if (status)
                this.value = (byte)(this.value | 0x04);
            else
                this.value = (byte)(this.value & 0xFB);
        }

        public boolean GetInvokeTraceroute()
        {
            if ((this.value & 0x08) == 0x08)
                return true;
            else return false;
        }

        public void SetInvokeTraceroute(boolean status)
        {
            if (status)
                this.value = (byte)(this.value | 0x08);
            else
                this.value = (byte)(this.value & 0xF7);
        }

        public static final Tx16TransmitOptions ForceLongHeader = new Tx16TransmitOptions((byte) 0x02);

        public static final Tx16TransmitOptions DisableLongHeader = new Tx16TransmitOptions((byte) 0x04);

        public static final Tx16TransmitOptions InvokeTraceroute = new Tx16TransmitOptions((byte) 0x08);

        public Tx16TransmitOptions()
        { }
        
        public Tx16TransmitOptions(byte value)
        { 
        	super(value);
        }

        public Tx16TransmitOptions(boolean disable_retries_and_route_repair, boolean force_long_header, boolean disable_long_header, boolean invoke_traceroute)
        {
            value = 0x00;
            if (disable_retries_and_route_repair)
                value = (byte)(value | 0x01);
            if (force_long_header)
                value = (byte)(value | 0x02);
            if (disable_long_header)
                value = (byte)(value | 0x04);
            if (invoke_traceroute)
                value = (byte)(value | 0x08);
        }
    }
