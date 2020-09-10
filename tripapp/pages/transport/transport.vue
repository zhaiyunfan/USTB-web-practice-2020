<template>
	    <div>
	      <div class="cu-bar bg-white margin-top solid-bottom">
	        <div class="action">
	          <text class="cuIcon-titles text-orange"></text>
	        </div>
	      </div>
	      <wuc-tab :tab-list="tabList" textFlex :tabCur.sync="TabCur" tab-class="text-center text-black bg-white" select-class="text-orange"></wuc-tab>
	      <swiper :current="TabCur" class="swiper" duration="300" :circular="true" indicator-color="rgba(255,255,255,0)" indicator-active-color="rgba(255,255,255,0)" @change="swiperChange">
	        <swiper-item v-for="(item,index) in tabList" :key="index">
	          <div class="bg-white padding margin text-center text-black">{{item.name}}</div>
	        </swiper-item>
	      </swiper>
	    </div>
</template>

<script>
	import WucTab from '@/components/wuc-tab/wuc-tab.vue';
	
	export default {
	    data() {
	        return {
	          TabCur: 0,
	          tabList: [{ name: '全部' }, { name: '火车' }, { name: '飞机' }, { name: '船舶' }], //修改对应的tab标签name
	        }},
	    components: { WucTab },
	    methods: {
	        tabChange(index) {
	            this.TabCur = index;
	        },
			swiperChange(e) {
            let { current } = e.target;
            this.TabCur = current;
			}
	    }
	}
</script>

<style>

</style>