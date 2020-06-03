package com.capitalone.dashboard.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="cpu_stats")
public class CPUStats extends BaseModel{
	
	private Date readAt;
	
	private Networks[] networks;
	
	private CPUStatsUsage statsUsage;
	
	private MemoryStats memoryStats;
	
	
	
	
	public Date getReadAt() {
		return readAt;
	}

	public void setReadAt(Date readAt) {
		this.readAt = readAt;
	}

	public Networks[] getNetworks() {
		return networks;
	}

	public void setNetworks(Networks[] networks) {
		this.networks = networks;
	}

	public CPUStatsUsage getStatsUsage() {
		return statsUsage;
	}

	public void setStatsUsage(CPUStatsUsage statsUsage) {
		this.statsUsage = statsUsage;
	}

	public MemoryStats getMemoryStats() {
		return memoryStats;
	}

	public void setMemoryStats(MemoryStats memoryStats) {
		this.memoryStats = memoryStats;
	}

	private class Networks {
			private Long rx_bytes; 
			private Long rx_dropped;
			private Long rx_errors;
			private Long rx_packets;
			private Long tx_bytes; 
			private Long tx_dropped;
			private Long tx_errors;
			private Long tx_packets;
			public Long getRx_bytes() {
				return rx_bytes;
			}
			public void setRx_bytes(Long rx_bytes) {
				this.rx_bytes = rx_bytes;
			}
			public Long getRx_dropped() {
				return rx_dropped;
			}
			public void setRx_dropped(Long rx_dropped) {
				this.rx_dropped = rx_dropped;
			}
			public Long getRx_errors() {
				return rx_errors;
			}
			public void setRx_errors(Long rx_errors) {
				this.rx_errors = rx_errors;
			}
			public Long getRx_packets() {
				return rx_packets;
			}
			public void setRx_packets(Long rx_packets) {
				this.rx_packets = rx_packets;
			}
			public Long getTx_bytes() {
				return tx_bytes;
			}
			public void setTx_bytes(Long tx_bytes) {
				this.tx_bytes = tx_bytes;
			}
			public Long getTx_dropped() {
				return tx_dropped;
			}
			public void setTx_dropped(Long tx_dropped) {
				this.tx_dropped = tx_dropped;
			}
			public Long getTx_errors() {
				return tx_errors;
			}
			public void setTx_errors(Long tx_errors) {
				this.tx_errors = tx_errors;
			}
			public Long getTx_packets() {
				return tx_packets;
			}
			public void setTx_packets(Long tx_packets) {
				this.tx_packets = tx_packets;
			}
			
			
	}
	
	public class MemoryStats{
		private Long usage;
		private Long maxUsage;
		private Long limit;
		public Long getUsage() {
			return usage;
		}
		public void setUsage(Long usage) {
			this.usage = usage;
		}
		public Long getMaxUsage() {
			return maxUsage;
		}
		public void setMaxUsage(Long maxUsage) {
			this.maxUsage = maxUsage;
		}
		public Long getLimit() {
			return limit;
		}
		public void setLimit(Long limit) {
			this.limit = limit;
		}
		
	}
	
	public class CPUStatsUsage{
		private Long systemCpuUsage;
		
		private Long totalUsage;

		public Long getSystemCpuUsage() {
			return systemCpuUsage;
		}

		public void setSystemCpuUsage(Long systemCpuUsage) {
			this.systemCpuUsage = systemCpuUsage;
		}

		public Long getTotalUsage() {
			return totalUsage;
		}

		public void setTotalUsage(Long totalUsage) {
			this.totalUsage = totalUsage;
		}
		
		
		
		
	}
	
	
}